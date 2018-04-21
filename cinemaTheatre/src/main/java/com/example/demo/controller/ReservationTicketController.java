package com.example.demo.controller;

import com.example.demo.dao.TicketsDao;
import com.example.demo.model.*;
import com.example.demo.service.*;
import com.example.demo.service.impl.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/reserve")
public class ReservationTicketController {

    @Autowired
    private CTService ctService;
    @Autowired
    private ReservationTicketService reservationTicketService;
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private HallService hallService;
    @Autowired
    private ProjectionService projectionService;
    @Autowired
    private TicketsDao ticketsDao;
    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;


    @RequestMapping(value = "/getCinemaTheater/{id}", method = RequestMethod.GET, produces = "application/json")
    public List<Projection> getCinemaTheater(@PathVariable("id") long id) {
        CinemaTheater cinema = ctService.find(id);
        List<Projection> projections = cinema.getProjections();
        List<String> names = new ArrayList<>();
        List<Projection> uniqueProjections = new ArrayList<>();
        uniqueProjections.addAll(projections);
        for (Projection p : projections) {
            if (names.contains(p.getName()))
                uniqueProjections.remove(p);
            else
                names.add(p.getName());
        }

        if (uniqueProjections == null)
            return null;
        return uniqueProjections;
    }

    @RequestMapping(value = "/getDatesOfProjection/{name}", method = RequestMethod.GET, produces = "application/json")
    public List<String> getDateOfProjection(@PathVariable("name") String name) {
        List<Projection> projections = projectionService.findProjectionsByName(name);
        List<String> dates = new ArrayList<>();
        for (Projection p : projections) {
            if (p.getDate() != null) {
                System.out.println(p.getDate() + " a u toString je " +
                        p.getDate().toString() + " a local time " + p.getDate().toLocalDate());
                if (dates.contains(p.getDate().toString()))
                    continue;
                else
                    dates.add(p.getDate().toString());
            }
        }
        if (dates == null)
            return null;
        return dates;
    }

    @RequestMapping(value = "/getTimesOfProjection/{name}/{date}", method = RequestMethod.GET, produces = "application/json")
    public List<String> getTimesOfProjection(@PathVariable("name") String name, @PathVariable("date") String date) {
        Date d = Date.valueOf(date);
        List<Projection> dates = projectionService.findProjectionsByNameAndDate(name, d);
        List<String> times = new ArrayList<>();
        if (dates == null || dates.isEmpty())
            return null;
        for (Projection p : dates){
            String pDate = p.getDate().toString() + " " + p.getTime().toString();
            String time = LocalDateTime.now().toString();
            String dateTime = time.substring(0, time.indexOf("T"));
            time = time.substring(time.indexOf("T") + 1, time.indexOf("."));
            dateTime += " " + time;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                java.util.Date covert = sdf.parse(dateTime);
                java.util.Date conDate = sdf.parse(pDate);
                long timestamp1 = conDate.getTime();
                long millis = covert.getTime();
                if (timestamp1 - millis > TimeUnit.MINUTES.toMillis(30))
                    times.add(p.getTime().toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }



        }
        if (times == null || times.isEmpty())
            return null;
        return times;
    }

    @RequestMapping(value = "/projectionSeats/{date}/{name}/{time}", method = RequestMethod.GET, produces = "application/json")
    public Projection getProjectionSeats(@PathVariable("date") String d,
                                              @PathVariable("name") String name, @PathVariable("time") String t) {
        Date date = Date.valueOf(d);
        Time time = Time.valueOf(t);
        Projection projection = projectionService.findFirstByNameAndDateAndTime(name, date, time);
        if (projection == null)
            return null;
        return projection;
    }



    @RequestMapping(value = "/getHall/{id}", method = RequestMethod.GET, produces = "application/json")
    public Hall getHall(@PathVariable("id") long id) {
        Projection projection = projectionService.findProjectionById(id);
        if (projection == null)
            return null;
        if (projection.getHall() == null)
            return null;
        return projection.getHall();

    }

    @Transactional
    @RequestMapping(value = "/getSeats/{idP}/{idH}", method = RequestMethod.GET, produces = "application/json")
    public List<Seat> getSeats(@PathVariable("idP") long idP,@PathVariable("idH") Long idH) {
        Hall provera = hallService.findById(idH);
        Projection projection = projectionService.findProjectionById(idP);
        Hall hall = projection.getHall();
        List<Seat> seats = reservationTicketService.findByProjectionAndHall(projection, hall);
        return seats;

    }

    @Transactional
    @PreAuthorize("hasAuthority('GUEST')")
    @RequestMapping(value = "/reserveTickets/{id}/{reservedSeats[]}", method = RequestMethod.GET)
    public String reserveTickets(@PathVariable(value = "reservedSeats[]") List<Integer> reservedSeats,
                                 @PathVariable("id") long id, HttpServletRequest request) {
        User user = authenticationService.getLoggedInUser();
        Projection projection = projectionService.findProjectionById(id);
        Hall hall = projection.getHall();
        List<Seat> seats = reservationTicketService.findByProjectionAndHall(projection, hall);
        boolean reserved = false;
        Seat seat;
        Ticket ticket;
        if (user == null)
            return "no user";
        if (reservedSeats == null)
            return "no tickets";
        for (Integer i : reservedSeats) {
            seat = reservationTicketService.findSeatById(i.longValue());
            if (seat == null)
                return "not reserved";
            for(Seat s: seats){
                    if (s.getId()==seat.getId() && s.getSeatType()==SeatType.AVAILABLE){
                        seats.get(seats.indexOf(s)).setSeatType(SeatType.TAKEN);
                        seat.setSeatType(SeatType.TAKEN);
                        ticket = reservationTicketService.findBySeat(seat);
                        if (ticket == null) {
                            ticketsDao.create((short) 0, 1, seat, projection);
                            ticket = reservationTicketService.findBySeat(seat);
                            user.getReservedTickets().add(ticket);
                            user.setPoints(user.getPoints()+5);
//                            authenticationService.saveUser(user);
                            userService.saveUser(user);
                            reserved = true;
                        }
                    }
            }
        }

        if(reserved){
            String message = "You have reserved projection of  " + projection.getName() + " on day: " + projection.getDate() + " at  " + projection.getTime();
            try {
                emailService.sendEmailVerification(user.getEmail(), message, "Reservation information");
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        } else{
            return "already";
        }
        return "ok";
    }

    @PreAuthorize("hasAuthority('GUEST')")
    @RequestMapping(value = "/removeTicket/{id}/{now}", method = RequestMethod.GET)
    public String removeTicket(@PathVariable("id") Long id, @PathVariable("now") String now) throws ParseException {
        User user = authenticationService.getLoggedInUser();
        if (user == null)
            return "no user";
        Ticket ticket = reservationTicketService.findTicketById(id);
        Projection projection = ticket.getProjection();
        Hall hall = projection.getHall();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            String convert = projection.getDate() + " "+ projection.getTime();
            java.util.Date covert = sdf.parse(convert);
            long timestamp1 = covert.getTime();
            long millis = Long.parseLong(now);
            if (timestamp1 - millis < TimeUnit.MINUTES.toMillis(30))
               return "30";
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (ticket.getStatus() != 1 && ticket.getStatus() != 3)
            return "1";
        Seat seat = ticket.getSeat();
        List<Seat> seats = reservationTicketService.findByProjectionAndHall(projection, hall);
        seats.get(seats.indexOf(seat)).setSeatType(SeatType.AVAILABLE);
        reservationTicketService.save(seat);
        user.setPoints(user.getPoints()-5);
        user.getReservedTickets().remove(ticket);
//        userService.saveUser(user);
        reservationTicketService.delete(ticket);
        return "ok";
    }


//    @PreAuthorize("hasAuthority('GUEST')")
    @RequestMapping(value = "/seeReservation/{idTicket}/{idUser}", method = RequestMethod.GET)
    public ResponseEntity seeReservation(@PathVariable("idTicket") Long idTicket, @PathVariable("idUser") Long idUser) {
        User user = authenticationService.getLoggedInUser();
        User clicked = userService.getById(idUser);
        if(user==null)
            return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header(HttpHeaders.LOCATION, "http://localhost:9080/invitationLogin.html").build();
        if (user.getId()!= clicked.getId())
            return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header(HttpHeaders.LOCATION, "http://localhost:9080/invitationLogin.html").build();

        Ticket t = reservationTicketService.findTicketById(idTicket);
        if (t == null)
            return null;
        if (t.getStatus() == 2) {
            t.setStatus(4);
            reservationTicketService.save(t);
        }
        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header(HttpHeaders.LOCATION, "http://localhost:9080/invitation.html").build();
    }

    @RequestMapping(value = "/seeReservationAfterLogin", method = RequestMethod.GET)
    public String seeReservationAfterLogin(){
        User user = authenticationService.getLoggedInUser();
        List<Ticket> tickets = user.getReservedTickets();
        if(tickets==null)
            return "no";
        for (Ticket t: tickets){
            if (t.getStatus() == 2) {
                t.setStatus(4);
                reservationTicketService.save(t);
                break;
            }
        }
        return "ok";
    }


    @PreAuthorize("hasAuthority('GUEST')")
    @RequestMapping(value = "/acceptInvitation", method = RequestMethod.GET)
    public String acceptInvitation() {
        User user = authenticationService.getLoggedInUser();
        List<Ticket> tickets = reservationTicketService.findAll();
        if (tickets.isEmpty())
            return null;
        for (Ticket t : tickets) {
            if (t.getStatus() == 4) {
                t.setStatus(3);
                reservationTicketService.save(t);
                user.setPoints(user.getPoints()+5);
                user.getReservedTickets().add(t);
//                userService.saveUser(user);
//                authenticationService.saveUser(user);
                break;
            }
        }
        return "ok";
    }

    @PreAuthorize("hasAuthority('GUEST')")
    @RequestMapping(value = "/declineInvitation", method = RequestMethod.GET)
    public String declineInvitation() {
        User user = authenticationService.getLoggedInUser();
        List<Ticket> tickets = reservationTicketService.findAll();
        if (tickets.isEmpty())
            return null;
        for (Ticket t : tickets) {
            if (t.getStatus() == 4) {
                Seat seat = t.getSeat();
                seat.setSeatType(SeatType.AVAILABLE);
                reservationTicketService.save(seat);
                user.getReservedTickets().remove(t);
                reservationTicketService.delete(t);
                user.setPoints(user.getPoints()-5);
//                userService.saveUser(user);
//                authenticationService.saveUser(user);
                break;
            }
        }
        return "ok";
    }

    @RequestMapping(value = "/tickets", method = RequestMethod.GET)
    public List<Ticket> getTickets(){
        User user = authenticationService.getLoggedInUser();
        List<Ticket> tickets = user.getReservedTickets();
        List<Ticket> ret = new ArrayList<>();
        if(tickets==null)
            return null;
        for(Ticket t: tickets){
            if (t.getStatus()==1 ||t.getStatus()==3)
                ret.add(t);
        }
        return ret;
    }
}

