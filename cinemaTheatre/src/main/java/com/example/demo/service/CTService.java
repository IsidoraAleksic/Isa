package com.example.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.model.CTType;
import com.example.demo.model.CinemaTheater;

import java.util.List;

public interface CTService {

    Page<CinemaTheater> getCinemaTheaterByType(CTType type, Pageable pageable);
    Page<CinemaTheater> getAllCinemaTheater( Pageable pageable);

    List<CinemaTheater> getCinemaTheatersByTypeOrderByName(CTType ctType);

    List<CinemaTheater> getCinemaTheatersByTypeOrderByAddress(CTType ctType);

    List<CinemaTheater> getCinemaTheatersByTypeOrderByAmbient(CTType ctType);
    
    CinemaTheater save(CinemaTheater ct);

    CinemaTheater find(long id);

    CinemaTheater delete(long id);

	CinemaTheater getCinemaTheaterByUser(long id);

}
