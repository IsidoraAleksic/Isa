package com.example.demo.converters;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.demo.dto.ProjectionDTO;
import com.example.demo.model.Hall;
import com.example.demo.model.Projection;
import com.example.demo.service.HallService;

@Component
public class ProjectionToProjectionDTO implements Converter<Projection, ProjectionDTO> {

	@Autowired
	private HallService hService;

	@Override
	public ProjectionDTO convert(Projection p) {

		if (p == null)
			return null;

		ModelMapper modelMapper = new ModelMapper();
		ProjectionDTO result = modelMapper.map(p, ProjectionDTO.class);

		Hall h = hService.findById(p.getHall().getId());

		result.setHall_id(h.getId());
		result.setHall_name(h.getName());

		return result;
	}

	public List<ProjectionDTO> convert(List<Projection> projections) {
		List<ProjectionDTO> projDTOs = new ArrayList<>();
		for (Projection p : projections)
			projDTOs.add(convert(p));
		return projDTOs;
	}

}
