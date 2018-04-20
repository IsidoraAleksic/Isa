package com.example.demo.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.demo.converters.ProjectionDTOtoProjection;
import com.example.demo.converters.ProjectionToProjectionDTO;
import com.example.demo.dto.ProjectionDTO;
import com.example.demo.model.Projection;
import com.example.demo.service.CTService;
import com.example.demo.service.ProjectionService;
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

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<?> getProjections() {
		return new ResponseEntity<>(pService.getAll(), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMINCT')")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<?> addProjection(@RequestBody ProjectionDTO pdto) {
		
		Projection created = pService.save(toProj.convert(pdto));
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
	public ResponseEntity<?> getStatistics(@PathVariable long id, @PathVariable String s, @PathVariable int num) {
		SimpleDateFormat form = new SimpleDateFormat("YYYY-MM-dd");
		Calendar cal = Calendar.getInstance();
		String newer, older;

		int calConst = s.equalsIgnoreCase("day") ? 5
				: s.equalsIgnoreCase("week") ? 4 : s.equalsIgnoreCase("month") ? 2 : -1;

		if (calConst == -1)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		List<Stat> stats = new ArrayList<>();

		for (int i = 0; i < num; i++) {
			newer = form.format(cal.getTime());
			cal.add(calConst, -1);
			if (calConst != 5)
				cal.add(Calendar.DATE, 1);
			older = form.format(cal.getTime());
			// System.out.print(s.equalsIgnoreCase("day") ? newer : older);
			// System.out.println(!s.equalsIgnoreCase("day") ? " - " + newer : "");
			int sum = 0;
			List<Projection> projs = new ArrayList<>();
			if (calConst == 5) {
				projs = pService.findByCtidAndDateLike(id, newer);
			} else {
				projs = pService.findByCtidAndDateBetween(id, older, newer);
			}
			for (Projection p : projs) {
				sum += p.getTaken().size();
			}
			stats.add(0, new Stat(new SimpleDateFormat("E, dd/MM").format(cal), sum));
			if (!s.equalsIgnoreCase("day"))
				cal.add(Calendar.DATE, -1);
		}

		return new ResponseEntity<>(stats, HttpStatus.OK);
	}

}
