package com.example.demo.service;

import com.example.demo.dto.CinemaTheatreRatingDTO;
import org.springframework.stereotype.Service;

@Service
public interface RatingService {

    String addCinemaTheatreAmbient(CinemaTheatreRatingDTO cinemaTheatreRatingDTO, Long userId);

    String addCinemaTheatreProjection(CinemaTheatreRatingDTO cinemaTheatreRatingDTO, Long userId);

}
