package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.demo.converters.HallDTOtoHall;
import com.example.demo.dto.HallDTO;
import com.example.demo.model.Hall;
import com.example.demo.service.HallService;

@RestController
@RequestMapping(value="/hall")
public class HallController {

	@Autowired
	private HallService hallService;

	@Autowired
	private HallDTOtoHall toHall;
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> getHall(@PathVariable("id")Long id){
		Hall hall = new Hall();//hallService.findById(id);
		return new ResponseEntity<>(hall, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<?> addHall(@Validated HallDTO hallDTO){
		hallService.save(toHall.convert(hallDTO));
		return new ResponseEntity<>(HttpStatus.OK);
	};

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteHall(@PathVariable long id){
		Hall h = hallService.delete(id);
		return new ResponseEntity<>(h,HttpStatus.OK);
	}
	
	
}
