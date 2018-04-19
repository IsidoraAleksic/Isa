package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Projection;
import com.example.demo.service.ProjectionService;

@RestController
@RequestMapping(value = "/projections")
public class ProjectionController {

	@Autowired
	private ProjectionService pService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<?> getProjections() {
		return new ResponseEntity<>(pService.getAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<?> addProjection(@Validated @RequestBody Projection p, Errors errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity<>(errors.getAllErrors().toString(), HttpStatus.BAD_REQUEST);
		}
		Projection created = pService.save(p);
		return new ResponseEntity<>(created, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteProjection(@PathVariable long id) {
		Projection deleted = pService.delete(id);
		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getProjection(@PathVariable long id) {
		return new ResponseEntity<>(pService.findById(id), HttpStatus.OK);
	}
	@RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
	public Projection getProjectionById(@PathVariable long id) {
		return pService.findProjectionById(id);
	}

}
