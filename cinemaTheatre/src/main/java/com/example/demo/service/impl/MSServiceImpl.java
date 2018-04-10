package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.MovieShow;
import com.example.demo.repository.MSRepository;
import com.example.demo.service.MSService;

@Service
public class MSServiceImpl implements MSService{
	
	@Autowired
	private MSRepository msRepository;

	@Override
	public MovieShow findById(long id) {
		return msRepository.findById(id);
	}

	@Override
	public MovieShow delete(long id) {
		MovieShow ms = msRepository.findById(id);
		if(ms == null)
			throw new IllegalArgumentException("Tried to delete non-existant Entity");
		return ms;
	}

	@Override
	public List<MovieShow> getAll() {
		List<MovieShow> ms = new ArrayList<>();
		ms = msRepository.findAll();
		return ms;
	}

	@Override
	public MovieShow save(MovieShow ms) {
		msRepository.save(ms);
		return ms;
	}

}
