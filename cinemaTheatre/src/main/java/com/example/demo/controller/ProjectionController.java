package com.example.demo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.demo.converters.ProjectionDTOtoProjection;
import com.example.demo.converters.ProjectionToProjectionDTO;
import com.example.demo.dto.ProjectionDTO;
import com.example.demo.model.CinemaTheater;
import com.example.demo.model.Hall;
import com.example.demo.model.Projection;
import com.example.demo.model.Seat;
import com.example.demo.model.SeatType;
import com.example.demo.model.Ticket;
import com.example.demo.model.User;
import com.example.demo.service.AuthenticationService;
import com.example.demo.service.CTService;
import com.example.demo.service.ProjectionService;
import com.example.demo.service.ReservationTicketService;
import com.example.demo.service.SeatService;
import com.example.demo.service.UserService;
import com.example.demo.util.Stat;

@RestController
@RequestMapping(value = "/projections")
public class ProjectionController {

	@Autowired
	private ProjectionService pService;

	@Autowired
	private ProjectionToProjectionDTO toDTO;

	@Autowired
	private ProjectionDTOtoProjection toProj;

	@Autowired
	private SeatService seatService;

	@Autowired
	private ReservationTicketService rts;

	@Autowired
	private CTService ctService;

	@Autowired
	private AuthenticationService authenticationService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<?> getProjections() {
		return new ResponseEntity<>(pService.getAll(), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMINCT')")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<?> addProjection(@RequestBody ProjectionDTO pdto) {

		Projection p = pService.save(toProj.convert(pdto));
		Hall hall = p.getHall();
		int rows = hall.getRows();
		int cols = hall.getCols();

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				SeatType type = (i == 0) ? SeatType.VIP
						: (i == 1) ? SeatType.SPEED
								: (i == rows - 1) ? SeatType.VIP
										: (i == rows - 2) ? SeatType.REDACTED : SeatType.AVAILABLE;
				Seat s = new Seat(i, j, type, p.getHall(), p);
				hall.addSeat(seatService.addSeat(s));
			}
		}
		p.getCt().addProjection(p);
		Projection created = pService.save(p);

		return new ResponseEntity<>(toDTO.convert(created), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMINCT')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteProjection(@PathVariable long id) {
		Projection deleted = pService.delete(id);
		return new ResponseEntity<>(toDTO.convert(deleted), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getProjection(@PathVariable long id) {
		return new ResponseEntity<>(pService.findById(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
	public Projection getProjectionById(@PathVariable long id) {
		return pService.findProjectionById(id);
	}

	@RequestMapping(value = "/ct/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getCTProjections(@PathVariable long id) {
		List<Projection> projections = pService.findByCtid(id);
		return new ResponseEntity<>(toDTO.convert(projections), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMINCT')")
	@RequestMapping(value = "stats/{id}/{s}/{num}", method = RequestMethod.GET)
	public ResponseEntity<?> getStatistics(@PathVariable long id, @PathVariable String s, @PathVariable int num)
			throws ParseException {
		SimpleDateFormat form = new SimpleDateFormat("YYYY-MM-dd");
		Calendar cal = Calendar.getInstance();
		String newer, older;

		int calConst = s.equalsIgnoreCase("day") ? 5
				: s.equalsIgnoreCase("week") ? 4 : s.equalsIgnoreCase("month") ? 2 : -1;

		if (calConst == -1)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		List<Stat> stats = new ArrayList<>();
		User user = authenticationService.getLoggedInUser();
		CinemaTheater ct = ctService.getCinemaTheaterByUser(user.getId());
		List<Projection> projs = ct.getProjections();

		System.out.println("const " + calConst);
		
		for (int i = 0; i < num; i++) {
			newer = form.format(cal.getTime());
			Date nd = cal.getTime();
			cal.add(calConst, -1);
			if (calConst != 5)
				cal.add(Calendar.DATE, 1);
			older = form.format(cal.getTime());
			Date od = cal.getTime();
			int sum = 0;
			System.out.println(i + ". " + older + " to " + newer);
			for (Projection pp : projs) {
				Date pDate = pp.getDate();
				if ((pDate.compareTo(nd) <  1) && (pDate.compareTo(od) > -1)) {
					sum += pp.getTaken().size();
					System.out.println(i + ". " +older + " - " + pDate.toString() + " - " + newer);
				}
			}
			String y = null;
			if (calConst == 5) {
				cal.add(Calendar.DATE, 1);
				y = new SimpleDateFormat("E, dd/MM").format(cal.getTime());
				cal.add(Calendar.DATE, -1);
			} else {
				y = new SimpleDateFormat("E, dd/MM").format(cal.getTime());
			}
			stats.add(0, new Stat(y, sum));
			if (!s.equalsIgnoreCase("day"))
				cal.add(Calendar.DATE, -1);
		}

		return new ResponseEntity<>(stats, HttpStatus.OK);
	}

}
