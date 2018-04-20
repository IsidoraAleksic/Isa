package com.example.demo.service.impl;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.CinemaTheater;
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
		pRepository.deleteById(id);
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
		return pRepository.findByCtId(id);
	}


    @Override
    public List<Projection> findProjectionsByName(String name) {
        return pRepository.findProjectionsByName(name);
    }

    @Override
    public List<Projection> findProjectionsByNameAndDate(String name, Date date) {
        return pRepository.findProjectionsByNameAndDate(name,date);
    }

	@Override
	public Projection findFirstByNameAndDateAndTime(String name, Date date, Time time) {
		return pRepository.findFirstByNameAndDateAndTime(name,date,time);
	}

	@Override
	public List<Projection> findByCtidAndDateBetween(long id, String dateStart, String dateEnd) {
		
		return pRepository.findByCtAndDateBetween(id, dateStart, dateEnd);
	}

	@Override
	public List<Projection> findByCtidAndDateLike(long id, String date) {
		return pRepository.findByCtAndDateLike(id, date);
	}



}
