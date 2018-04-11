package com.example.demo.repository;

import com.example.demo.model.ReservationMerchandise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationMerchandiseRepository  extends JpaRepository<ReservationMerchandise,Long> {
    ReservationMerchandise getById(Long id_reservationMerch);
    List<ReservationMerchandise> findByUserId(Long user_id);
}
