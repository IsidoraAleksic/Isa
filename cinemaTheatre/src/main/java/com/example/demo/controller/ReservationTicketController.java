package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.service.AuthenticationService;
import com.example.demo.service.CTService;
import com.example.demo.service.ProjectionService;
import com.example.demo.service.ReservationTicketService;
import com.example.demo.service.impl.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

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
    private EmailService emailService;
    @Autowired
    private ProjectionService projectionService;

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
    public List<Date> getDateOfProjection(@PathVariable("name") String name) {
        List<Projection> projections = projectionService.findProjectionsByName(name);
        List<Date> dates = new ArrayList<>();
        if (projections == null)
            return null;
        for (Projection p : projections)
            dates.add(p.getDate());
        if (dates == null)
            return null;
        return dates;
    }

    @RequestMapping(value = "/getTimesOfProjection/{name}/{date}", method = RequestMethod.GET, produces = "application/json")
    public List<Time> getTimesOfProjection(@PathVariable("name") String name, @PathVariable("date") String date) {
        Date d = Date.valueOf(date);
        List<Projection> dates = projectionService.findProjectionsByNameAndDate(name, d);
        List<Time> times = new ArrayList<>();
        if (dates == null || dates.isEmpty())
            return null;
        for (Projection p : dates)
            times.add(p.getTime());
        if (times == null || times.isEmpty())
            return null;
        return times;
    }

    @RequestMapping(value = "/getTimesOfProjection/{name}/{date}/{time}", method = RequestMethod.GET, produces = "application/json")
    public Hall getHallAndSeatsForReservation(@PathVariable("name") String name,
                                              @PathVariable("date") String d, @PathVariable("time") String t) {
        Date date = Date.valueOf(d);
        Time time = Time.valueOf(t);
        Projection projection = projectionService.findFirstByNameAndDateAndTime(name, date, time);
        if (projection == null)
            return null;
        return projection.getHall();
    }

    @RequestMapping(value = "/reserveTicket/{id}/{row}/{col}", method = RequestMethod.GET, produces = "text/plain")
    public String reserveTicket(@PathVariable("id") Long id, @PathVariable("row") String row, @PathVariable("col") String col) {
        User user = authenticationService.getLoggedInUser();
        int r = Integer.parseInt(row);
        int c = Integer.parseInt(col);
        Seat seat = reservationTicketService.findByRowAndCol(r, c);
        Projection projection = projectionService.findById(id);
        Reservation reservation = reservationTicketService.findByProjection(projection);
        if (user == null)
            return "no user";
        if (seat == null)
            return "no seat";
        if (projection == null)
            return "no projection";
        if (reservation == null)
            return "no reservation";
        seat.setSeatType(SeatType.TAKEN);
        user.getReservedTickets().add(reservation);
        return "ok";
    }

}
