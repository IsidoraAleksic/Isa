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
public class CTServiceImpl implements CTService {

	@Autowired
	private CTRepository ctRepository;

	@Override
	public Page<CinemaTheater> getCinemaTheaterByType(CTType ct, Pageable pageable) {
		return ctRepository.getCinemaTheaterByType(ct, pageable);
	}

	@Override
	public CinemaTheater save(CinemaTheater ct) {
		return ctRepository.save(ct);
	}

	@Override
	public CinemaTheater find(long id) {
		return ctRepository.findById(id);
	}

	@Override
	public CinemaTheater delete(long id) {
		CinemaTheater ct = ctRepository.findById(id);
		if(ct == null)
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant Entity");
		ctRepository.delete(ct);
		return ct;
		
	}

}
