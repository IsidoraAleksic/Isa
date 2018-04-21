package com.example.demo.controller;

import com.example.demo.dto.CTDTO;
import com.example.demo.dto.CinemaTheatreRatingDTO;
import com.example.demo.model.CinemaTheater;
import com.example.demo.service.AuthenticationService;
import com.example.demo.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    RatingService ratingService;

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/cinemaTheatre/ambient")
    public ResponseEntity ratingCinemaTheatreAmbient(@RequestBody CinemaTheatreRatingDTO cinemaTheaterRatingDTO) {

        return new ResponseEntity<>("", HttpStatus.OK);
    }

}
