package com.example.demo.controller;

import com.example.demo.model.ReservationMerchandise;
import com.example.demo.service.ReservationMerchandiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reservationMerch")
public class ReservationMerchandiseController {

    private ReservationMerchandiseService  reservationMerchandiseService;

    @Autowired
    public ReservationMerchandiseController(ReservationMerchandiseService reservationMerchandiseService) {
        this.reservationMerchandiseService = reservationMerchandiseService;
    }

    @RequestMapping("{id}")
    public ReservationMerchandise getById(@PathVariable("id") Long id_reservationMerch){
        return reservationMerchandiseService.getById(id_reservationMerch);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity findUserReservations(@PathVariable Long userId) {
        final List<ReservationMerchandise> userReservations = reservationMerchandiseService.findUserReservations(userId);
        return new ResponseEntity<>(userReservations, HttpStatus.OK);
    }

}
