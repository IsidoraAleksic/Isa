package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Hall;

public interface HallService {
	
	Hall save(Hall h);
	
	Hall delete(Long id);

	Hall findById(long id);

	List<Hall> findByCTId(long id);

}
