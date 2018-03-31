package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.model.CTType;
import com.example.demo.model.CinemaTheater;
import com.example.demo.repository.CTRepository;
import com.example.demo.service.CTService;

@Service
public class CTServiceImpl implements CTService{

	@Autowired
	private CTRepository ctRepository;
	
	@Override
	public Page<CinemaTheater> getCinemaTheaterByType(CTType theater, Pageable pageable) {
		return ctRepository.getCinemaTheaterByType(theater, pageable);
	}

}
