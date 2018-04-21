package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.demo.converters.HallDTOtoHall;
import com.example.demo.converters.HallToHallDTO;
import com.example.demo.dto.HallDTO;
import com.example.demo.model.Hall;
import com.example.demo.service.HallService;

@RestController
@RequestMapping(value = "/hall")
public class HallController {

	@Autowired
	private HallService hallService;

	@Autowired
	private HallDTOtoHall toHall;

	@Autowired
	private HallToHallDTO toHallDTO;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getHall(@PathVariable("id") Long id) {
		Hall hall = hallService.findById(id);
		return new ResponseEntity<>(toHallDTO.convert(hall), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMINCT')")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<?> addHall(@RequestBody HallDTO hallDTO) {
		Hall saved = hallService.save(toHall.convert(hallDTO));
		return new ResponseEntity<>(toHallDTO.convert(saved), HttpStatus.OK);
	};

	@PreAuthorize("hasAuthority('ADMINCT')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteHall(@PathVariable long id) {
		Hall h = hallService.delete(id);
		return new ResponseEntity<>(toHallDTO.convert(h), HttpStatus.OK);
	}

	@RequestMapping(value = "/halls/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getCTHalls(@PathVariable long id) {
		List<Hall> halls = hallService.findByCTId(id);
		return new ResponseEntity<>(toHallDTO.convert(halls), HttpStatus.OK);
	}

}
