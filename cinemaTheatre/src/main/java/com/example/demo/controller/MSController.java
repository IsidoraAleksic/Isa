package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.demo.converters.MStoMSDTO;
import com.example.demo.model.MovieShow;
import com.example.demo.service.MSService;

@RestController
@RequestMapping(value = "/ms")
public class MSController {

	@Autowired
	private MSService msService;

	@Autowired
	private MStoMSDTO toMSDTO;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAll() {
		return new ResponseEntity<>(toMSDTO.convert(msService.getAll()), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addMS(@Validated @RequestBody MovieShow ms) {
		return new ResponseEntity<>(toMSDTO.convert(msService.save(ms)), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteMS(long id) {
		return new ResponseEntity<>(toMSDTO.convert(msService.delete(id)), HttpStatus.OK);
	}

}
