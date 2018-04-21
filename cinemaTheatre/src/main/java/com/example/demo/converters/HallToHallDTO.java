package com.example.demo.converters;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.demo.dto.HallDTO;
import com.example.demo.model.Hall;

@Component
public class HallToHallDTO implements Converter<Hall, HallDTO> {

	@Override
	public HallDTO convert(Hall hall) {

		if (hall == null)
			return null;

		ModelMapper modelMapper = new ModelMapper();

		HallDTO result = modelMapper.map(hall, HallDTO.class);
		return result;

	}

	public List<HallDTO> convert(List<Hall> halls) {
		List<HallDTO> hallDTOs = new ArrayList<>();
		for (Hall h : halls) {
			hallDTOs.add(convert(h));
		}
		return hallDTOs;
	}

}
