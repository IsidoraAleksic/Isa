package com.example.demo.service;

import com.example.demo.model.Projection;

import java.util.Date;
import java.sql.Time;
import java.util.List;

public interface ProjectionService {


	public List<Projection> findByCtid(long id);

    Projection findProjectionById(long id);

    Projection save(Projection projection);

    Projection delete(long id);

    List<Projection> getAll();

    Projection findById(long id);

    List<Projection> findProjectionsByName(String name);

    List<Projection> findProjectionsByNameAndDate(String name, Date date);

    Projection findFirstByNameAndDateAndTime(String name, Date date, Time time);

    List<Projection> findByCtidAndDateBetween(long id, Date dateStart, Date dateEnd);

    List<Projection> findByCtidAndDateLike(long id, Date first);

    List<Projection> findByDateBetween(Date dateStart, Date dateEnd);

    List<Projection> findByDateLike(Date first);

}
