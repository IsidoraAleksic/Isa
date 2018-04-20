package com.example.demo.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.demo.dto.SeatDTO;
import com.example.demo.model.Seat;

@Component
public class SeatToSeatDTO implements Converter<Seat, SeatDTO>{

	@Override
	public SeatDTO convert(Seat seat) {

		if (seat == null)
			return null;
		
		SeatDTO result = new SeatDTO();
		result.setId(seat.getId());
		result.setRow(seat.getRow());
		result.setCol(seat.getCol());
		result.setTime(seat.getProjection().getTime().toString());
		result.setHall_name(seat.getHall().getName());
		result.setPrice(seat.getProjection().getPrice());
		result.setMovie_name(seat.getProjection().getName());	
		
		return result;
	}

	public List<SeatDTO> convert(List<Seat> seats){
		List<SeatDTO> dtos = new ArrayList<>();
		for(Seat seat : seats) {
			dtos.add(convert(seat));
		}
		return dtos;
	}
	
}
