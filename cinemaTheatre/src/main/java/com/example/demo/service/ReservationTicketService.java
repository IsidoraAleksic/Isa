package com.example.demo.service;

import com.example.demo.model.Hall;
import com.example.demo.model.Projection;
import com.example.demo.model.Seat;
import com.example.demo.model.Ticket;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface ReservationTicketService {

    Seat findByRowAndCol(int row, int col);

    Ticket findByProjection(Projection projection);

    List<Seat> findByProjectionAndHall(Projection projection, Hall hall);

    Ticket findBySeat(Seat seat);

//    Ticket findTicketBySeatAndHallAndProjection(Seat seat, Hall hall, Projection projection);

    Ticket findTicketById(Long id);

//    List<Ticket> findByUser(User user);

    List<Ticket> findAll();

    Seat findSeatById(Long id);

    List<Seat> findByHall(Hall hall);

    void delete(Ticket ticket);

    void save(Ticket ticket);

    void save(Seat seat);

}
