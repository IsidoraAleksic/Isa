package com.example.demo.repository;

import com.example.demo.model.CinemaTheater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.demo.model.Projection;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Component
public interface ProjectionRepository extends JpaRepository<Projection, Long>{

    List<Projection> findProjectionsByName(String name);
    List<Projection> findProjectionsByNameAndDate(String name, Date date);
    Projection findFirstByNameAndDateAndTime(String name, Date date, Time time);



}
