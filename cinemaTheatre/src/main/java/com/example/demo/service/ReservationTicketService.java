package com.example.demo.service;

import com.example.demo.model.Projection;
import com.example.demo.model.Reservation;
import com.example.demo.model.Seat;
import org.springframework.stereotype.Service;

@Service
public interface ReservationTicketService {

    Seat findByRowAndCol(int row, int col);
    Reservation findByProjection(Projection projection);
}
