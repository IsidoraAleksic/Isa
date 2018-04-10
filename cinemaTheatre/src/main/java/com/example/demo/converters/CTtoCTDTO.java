package com.example.demo.converters;

import org.springframework.stereotype.Component;

import com.example.demo.dto.CTDTO;
import com.example.demo.model.CinemaTheater;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;

@Component
public class CTtoCTDTO implements Converter<CinemaTheater, CTDTO> {

	@Override
	public CTDTO convert(CinemaTheater ct) {

		if (ct == null)
			return null;

		ModelMapper modelMapper = new ModelMapper();

		CTDTO result = modelMapper.map(ct, CTDTO.class);
		return result;

	}

	public List<CTDTO> convert(List<CinemaTheater> cts) {
		List<CTDTO> result = new ArrayList<>();

		for (CinemaTheater ct : cts)
			result.add(convert(ct));

		return result;
	}

}
