package com.example.demo.converters;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.demo.dto.ProjectionDTO;
import com.example.demo.model.Projection;
import com.example.demo.service.CTService;
import com.example.demo.service.HallService;

@Component
public class ProjectionDTOtoProjection implements Converter<ProjectionDTO, Projection>{

	@Autowired
	private CTService ctService;
	
	@Autowired
	private HallService hService;
	
	@Override
	public Projection convert(ProjectionDTO pdto) {
		
		if(pdto == null)
			return null;
		
		Projection p = new Projection();
		p.setName(pdto.getName());
		p.setActors(pdto.getActors());
		p.setGenre(pdto.getGenre());
		p.setDirector(pdto.getDirector());
		p.setDuration(pdto.getDuration());
		p.setImagePath(pdto.getImagePath());
		p.setDescription(pdto.getDescription());
		p.setHall(hService.findById(pdto.getHall_id()));
		p.setCt(ctService.find(pdto.getCt_id()));
		p.setPrice(pdto.getPrice());
		p.setDate(java.sql.Date.valueOf(pdto.getDate()));
		String time = pdto.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:MM");
		long ms = 0;
		try {
			ms = sdf.parse(time).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		p.setTime(new java.sql.Time(ms));
		
		return p;
	}

}
