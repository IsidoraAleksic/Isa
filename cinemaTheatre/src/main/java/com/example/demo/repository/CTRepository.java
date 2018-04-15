package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import com.example.demo.model.CTType;
import com.example.demo.model.CinemaTheater;

import java.util.List;

@Repository
public interface CTRepository extends JpaRepository<CinemaTheater, Long> {

    public Page<CinemaTheater> getCinemaTheaterByType(CTType ctType, Pageable pageable);

    List<CinemaTheater> getCinemaTheatersByTypeOrderByName(CTType ctType);

    List<CinemaTheater> getCinemaTheatersByTypeOrderByAddress(CTType ctType);

    List<CinemaTheater> getCinemaTheatersByTypeOrderByAmbient(CTType ctType);

    public CinemaTheater findById(long id);

}
