package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.ReservationTicketRepository;
import com.example.demo.repository.SeatRepository;
import com.example.demo.service.ReservationTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



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
    public Ticket findByProjection(Projection projection) {
        return reservationTicketRepository.findByProjection(projection);
    }

    @Override
    public List<Seat> findByProjectionAndHall(Projection projection, Hall hall) {
        return seatRepository.findByProjectionAndHall(projection,hall);
    }

    @Override

    public Ticket findBySeat(Seat seat) {
        return reservationTicketRepository.findBySeat(seat);
    }

//    @Override
//    public Ticket findTicketBySeatAndHallAndProjection(Seat seat, Hall hall, Projection projection) {
//        return reservationTicketRepository.findTicketBySeatAndHallAndProjection(seat,hall,projection);
//    }

    @Override
    public Ticket findTicketById(Long id) {
        return reservationTicketRepository.findTicketById(id);
    }

    @Override
    public List<Ticket> findAll() {
        return reservationTicketRepository.findAll();
    }

//    @Override
//    public List<Ticket> findByUser(User user) {
//        return reservationTicketRepository.findByUser(user);
//    }

    @Override
    public Seat findSeatById(Long id) {
        return seatRepository.findSeatById(id);
    }


    @Override
    public List<Seat> findByHall(Hall hall) {
        return seatRepository.findByHall(hall);
    }

    @Override
    public void delete(Ticket ticket) {
        reservationTicketRepository.delete(ticket);
    }


    @Override
    public void save(Ticket ticket) {
        reservationTicketRepository.save(ticket);
    }


    @Override
    public void save(Seat seat) {
        seatRepository.save(seat);
    }
}
