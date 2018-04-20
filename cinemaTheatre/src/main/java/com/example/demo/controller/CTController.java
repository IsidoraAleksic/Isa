package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.converters.CTtoCTDTO;
import com.example.demo.dto.CTDTO;
import com.example.demo.model.CTType;
import com.example.demo.model.CinemaTheater;
import com.example.demo.service.CTService;

import java.util.List;

@RestController
@RequestMapping(value = "/")
public class CTController {

	@Autowired
	private CTService ctService;

	@Autowired
	private CTtoCTDTO toCTDTO;

	@Autowired
	private AuthenticationController authenticationController;

	@RequestMapping(value = "/cinemas", method = RequestMethod.GET)
	public Page<?> getCinemas(Pageable p) {
		Page<CinemaTheater> page = ctService.getCinemaTheaterByType(CTType.CINEMA, p);
		return new PageImpl<CTDTO>(toCTDTO.convert(page.getContent()), p, page.getTotalElements());
	}

	@RequestMapping(value = "/theaters", method = RequestMethod.GET)
	public Page<?> getTheaters(Pageable p) {
		Page<CinemaTheater> page = ctService.getCinemaTheaterByType(CTType.THEATER, p);
		return new PageImpl<CTDTO>(toCTDTO.convert(page.getContent()), p, page.getTotalElements());
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<?> addCT(@Validated @RequestBody CinemaTheater ct, Errors errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity<>(errors.getAllErrors().toString(), HttpStatus.BAD_REQUEST);
		}
		CinemaTheater created = ctService.save(ct);
		return new ResponseEntity<>(created, HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/ct/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteCT(@PathVariable long id) {
		CinemaTheater deleted = ctService.delete(id);
		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}

	@RequestMapping(value = "/ct/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getCT(@PathVariable long id) {
		return new ResponseEntity<>(ctService.find(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/sortCinemasBy/{criteria}", method = RequestMethod.GET, produces = "application/json")
	public List<CTDTO> sortCinemasBy(@PathVariable("criteria") String criteria) {
		List<CTDTO> cinemas;
		switch (criteria) {
		case "name":
			cinemas = toCTDTO.convert(ctService.getCinemaTheatersByTypeOrderByName(CTType.CINEMA));
			break;
		case "distance":
			cinemas = toCTDTO.convert(ctService.getCinemaTheatersByTypeOrderByAddress(CTType.CINEMA));
			break;
		case "rating":
			cinemas = toCTDTO.convert(ctService.getCinemaTheatersByTypeOrderByAmbient(CTType.CINEMA));
			break;
		default:
			cinemas = null;
		}
		return cinemas;

	}

	@RequestMapping(value = "/sortTheatersBy/{criteria}", method = RequestMethod.GET, produces = "application/json")
	public List<CTDTO> sortTheatersBy(@PathVariable("criteria") String criteria) {
		List<CTDTO> theaters;
		switch (criteria) {
		case "distance":
			theaters = toCTDTO.convert(ctService.getCinemaTheatersByTypeOrderByAddress(CTType.THEATER));
			break;
		case "name":
			theaters = toCTDTO.convert(ctService.getCinemaTheatersByTypeOrderByName(CTType.THEATER));
			break;
		case "rating":
			theaters = toCTDTO.convert(ctService.getCinemaTheatersByTypeOrderByAmbient(CTType.THEATER));
			break;
		default:
			theaters = null;
		}
		return theaters;

	}

	@RequestMapping(value = "/admin/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getByAdminID(@PathVariable long id) {
		CinemaTheater ct = ctService.getCinemaTheaterByUser(id);
		return new ResponseEntity<>(toCTDTO.convert(ct), HttpStatus.OK);
	}

	@RequestMapping(value = "/ct/{id}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<?> updateCT(@RequestBody CinemaTheater ct) {
		System.out.println(ct.getId() + "\n" + ct.getAddress() + "\n" + ct.getName() + "\n" + ct.getDescription());
		CinemaTheater edit = ctService.find(ct.getId());
		if (edit.getUser().getId() == authenticationController.getLoggedInUser().getId()) {
			if (ct.getName() != null || ct.getName().trim().equals(""))
				edit.setName(ct.getName());
			if (ct.getAddress() != null || ct.getAddress().trim().equals(""))
				edit.setAddress(ct.getAddress());
			if (ct.getDescription() != null || ct.getDescription().trim().equals(""))
				edit.setDescription(ct.getDescription());
			
			ctService.save(edit);

			return new ResponseEntity<>("ok",HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
