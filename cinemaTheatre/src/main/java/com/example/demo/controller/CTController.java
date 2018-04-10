package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping(value = "/")
public class CTController {

	@Autowired
	private CTService ctService;
	
	@Autowired
	private CTtoCTDTO toCTDTO;

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
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<?> addCT(@Validated @RequestBody CinemaTheater ct, Errors errors) {
		if(errors.hasErrors()) {
			return new ResponseEntity<>(errors.getAllErrors().toString(), HttpStatus.BAD_REQUEST);
		}
		CinemaTheater created = ctService.save(ct);
		return new ResponseEntity<>(created,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/cinemas/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteCT(@PathVariable long id){
		CinemaTheater deleted = ctService.delete(id);
		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}

	
}
