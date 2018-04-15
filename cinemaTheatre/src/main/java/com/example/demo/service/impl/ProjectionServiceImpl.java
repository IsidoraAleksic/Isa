package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Projection;
import com.example.demo.repository.ProjectionRepository;
import com.example.demo.service.ProjectionService;

@Service
public class ProjectionServiceImpl implements ProjectionService{

	@Autowired
	private ProjectionRepository pRepository;
	
	@Override
	public Projection save(Projection projection) {
		return pRepository.save(projection);
	}

	@Override
	public Projection delete(long id) {
		Projection p = pRepository.getOne(id);
		if(p == null)
			throw new IllegalArgumentException("Tried to delete non-existing projection");
		return p;
	}

	@Override
	public List<Projection> getAll() {
		List<Projection> projections = new ArrayList<>();
		projections = pRepository.findAll();
		return projections;
	}

	@Override
	public Projection findById(long id) {
		return pRepository.getOne(id);
	}

	@Override
	public List<Projection> findByCtid(long id) {
		return pRepository.findByCt(id);
	}

}
