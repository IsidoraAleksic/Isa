package com.example.demo.service.impl;

import com.example.demo.model.Projection;
import com.example.demo.model.Reservation;
import com.example.demo.model.Seat;
import com.example.demo.repository.ReservationTicketRepository;
import com.example.demo.repository.SeatRepository;
import com.example.demo.service.ReservationTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationTicketServiceImpl implements ReservationTicketService {
    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private ReservationTicketRepository reservationTicketRepository;

    @Override
    public Seat findByRowAndCol(int row, int col) {
        return seatRepository.findByRowAndCol(row,col);
    }

    @Override
    public Reservation findByProjection(Projection projection) {
        return reservationTicketRepository.findByProjection(projection);
    }
}
