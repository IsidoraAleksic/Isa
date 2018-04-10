package com.example.demo.service;

import java.util.List;

import com.example.demo.model.MovieShow;

public interface MSService {

	public MovieShow findById(long id);
	
	public MovieShow delete(long id);
	
	public List<MovieShow> getAll();
	
	public MovieShow save(MovieShow ms);
	
}
