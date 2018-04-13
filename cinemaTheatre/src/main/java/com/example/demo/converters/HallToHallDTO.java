package com.example.demo.converters;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;

import com.example.demo.dto.HallDTO;
import com.example.demo.model.Hall;

public class HallToHallDTO implements Converter<HallDTO, Hall> {

	@Override
	public Hall convert(HallDTO hallDTO) {

		if (hallDTO == null)
			return null;

		ModelMapper modelMapper = new ModelMapper();

		Hall result = modelMapper.map(hallDTO, Hall.class);
		return result;

	}

	public List<Hall> convert(List<HallDTO> hallDTOs) {
		List<Hall> halls = new ArrayList<Hall>();
		for (HallDTO hallDTO : hallDTOs) {
			halls.add(convert(hallDTO));
		}
		return halls;
	}
}
