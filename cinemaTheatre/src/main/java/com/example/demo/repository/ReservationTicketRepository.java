package com.example.demo.repository;

import com.example.demo.model.Projection;
import com.example.demo.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationTicketRepository extends JpaRepository<Reservation,Long> {

    Reservation findByProjection(Projection projection);
}
