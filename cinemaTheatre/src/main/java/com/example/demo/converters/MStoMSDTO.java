package com.example.demo.converters;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.demo.dto.MSDTO;
import com.example.demo.model.MovieShow;

@Component
public class MStoMSDTO implements Converter<MovieShow, MSDTO>{

	@Override
	public MSDTO convert(MovieShow ms) {

		if (ms == null)
			return null;

		ModelMapper modelMapper = new ModelMapper();

		MSDTO result = modelMapper.map(ms, MSDTO.class);
		return result;
		
	}
	
	public List<MSDTO> convert(List<MovieShow> ms){
		List<MSDTO> mss = new ArrayList<MSDTO>();
		for(MovieShow m : ms) {
			mss.add(convert(m));
		}
		return mss;
	}

}
