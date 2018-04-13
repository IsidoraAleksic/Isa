package com.example.demo.service;

import com.example.demo.dto.ReservationMerchandiseDTO;
import com.example.demo.model.ReservationMerchandise;

import java.util.List;

public interface ReservationMerchandiseService {
    public ReservationMerchandise getById(Long id_reservationMerch);
    public List<ReservationMerchandise> findUserReservations(Long user_id);
    public String createReservationMerchandise(ReservationMerchandiseDTO reservationMerchandiseDTO);
}
