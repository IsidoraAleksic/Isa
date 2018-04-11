package com.example.demo.service.impl;

import com.example.demo.model.ReservationMerchandise;
import com.example.demo.repository.ReservationMerchandiseRepository;
import com.example.demo.service.ReservationMerchandiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReservationMerchandiseServiceImpl implements ReservationMerchandiseService{

    private ReservationMerchandiseRepository reservationMerchandiseRepository;

    @Autowired
    ReservationMerchandiseServiceImpl(ReservationMerchandiseRepository reservationMerchandiseRepository){
        this.reservationMerchandiseRepository = reservationMerchandiseRepository;
    }

    public ReservationMerchandise getById(Long id_reservationMerch){
        return reservationMerchandiseRepository.getById(id_reservationMerch);
    }
    public List<ReservationMerchandise> findUserReservations(Long user_id){
        return reservationMerchandiseRepository.findByUserId(user_id);
    }


}
