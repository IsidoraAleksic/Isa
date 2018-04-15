package com.example.demo.service;

import com.example.demo.model.Projection;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public interface ProjectionService {

    Projection save(Projection projection);

    Projection delete(long id);

    List<Projection> getAll();

    Projection findById(long id);

    List<Projection> findProjectionsByName(String name);

    List<Projection> findProjectionsByNameAndDate(String name, Date date);

    Projection findFirstByNameAndDateAndTime(String name, Date date, Time time);


}
