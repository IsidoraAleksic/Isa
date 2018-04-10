package com.example.demo.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Hall;
import com.example.demo.repository.HallRepository;
import com.example.demo.service.HallService;

@Service
public class HallServiceImpl implements HallService {

	@Autowired
	private HallRepository hallRepository;

	@Override
	public Hall save(Hall h) {
		return hallRepository.save(h);
	}

	@Override
	public Hall delete(Long id) {
		Hall h = hallRepository.getOne(id);
		if(h == null)
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant hall");
		hallRepository.deleteById(id);
		return h;
		
	}

	@Override
	public Hall findById(long id) {
		return hallRepository.findById(id);
	}

}
