package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Projection;

public interface ProjectionService {

	public Projection save(Projection projection);
	
	public Projection delete(long id);
	
	public List<Projection> getAll();
	
	public Projection findById(long id);
	
	public List<Projection> findByCtid(long id);
	
}
