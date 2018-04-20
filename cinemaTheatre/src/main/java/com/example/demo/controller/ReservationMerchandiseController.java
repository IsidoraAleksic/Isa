package com.example.demo.controller;

import com.example.demo.dto.ReservationMerchandiseDTO;
import com.example.demo.model.ReservationMerchandise;
import com.example.demo.service.AuthenticationService;
import com.example.demo.service.ReservationMerchandiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservationMerch")
public class ReservationMerchandiseController {

    @Autowired
    private ReservationMerchandiseService  reservationMerchandiseService;

    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping("{id}")
    public ReservationMerchandise getById(@PathVariable("id") Long id_reservationMerch){
        return reservationMerchandiseService.getById(id_reservationMerch);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity findUserReservations(@PathVariable Long userId) {
        final List<ReservationMerchandise> userReservations = reservationMerchandiseService.findUserReservations(userId);
        return new ResponseEntity<>(userReservations, HttpStatus.OK);
    }

    @Transactional(rollbackFor = Exception.class)
    @PreAuthorize("hasAuthority('GUEST')")
    @PostMapping
    public ResponseEntity createReservationMerchandise(@RequestBody ReservationMerchandiseDTO reservationMerchandiseDTO) {
        reservationMerchandiseDTO.setUserId(authenticationService.getLoggedInUser().getId());
        String result = reservationMerchandiseService.createReservationMerchandise(reservationMerchandiseDTO);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

}
