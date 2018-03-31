package com.example.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.model.CTType;
import com.example.demo.model.CinemaTheater;

public interface CTService {
	
	Page<CinemaTheater> getCinemaTheaterByType(CTType type, Pageable pageable);

	
	
}
