package com.example.demo.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;

import com.example.demo.dto.HallDTO;
import com.example.demo.model.Hall;
import com.example.demo.service.CTService;

@Component
public class HallDTOtoHall implements Converter<HallDTO, Hall> {

	@Autowired
	private CTService ctService;
	
	@Override
	public Hall convert(HallDTO hallDTO) {
		if (hallDTO == null)
			return null;

		Hall hall = new Hall();

		hall.setName(hallDTO.getName());
		hall.setCols(hallDTO.getCols());
		hall.setRows(hallDTO.getRows());
		hall.setCt(ctService.find(hallDTO.getCtId()));
		
		return hall;
	}

}
