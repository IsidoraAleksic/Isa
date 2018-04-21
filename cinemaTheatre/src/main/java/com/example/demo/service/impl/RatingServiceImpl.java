package com.example.demo.service.impl;

import com.example.demo.dto.CinemaTheatreRatingDTO;
import com.example.demo.model.Rating;
import com.example.demo.repository.CTRepository;
import com.example.demo.repository.RatingRepository;
import com.example.demo.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    RatingRepository ratingRepository;


    public String addCinemaTheatreAmbient(CinemaTheatreRatingDTO cinemaTheatreRatingDTO, Long userId){

        return "";
    }

    public String addCinemaTheatreProjection(CinemaTheatreRatingDTO cinemaTheatreRatingDTO, Long userId){

        return "";
    }
}
