package com.example.demo.service;

import com.example.demo.model.Projection;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public interface ProjectionService {


	public Projection save(Projection projection);

	public Projection delete(long id);

	public List<Projection> getAll();

	public Projection findById(long id);

	public List<Projection> findByCtid(long id);


    Projection save(Projection projection);

    Projection delete(long id);

    List<Projection> getAll();

    Projection findById(long id);

    List<Projection> findProjectionsByName(String name);

    List<Projection> findProjectionsByNameAndDate(String name, Date date);

    Projection findFirstByNameAndDateAndTime(String name, Date date, Time time);


}
