package com.example.demo.repository;

import com.example.demo.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReservationTicketRepository extends JpaRepository<Ticket,Long> {

    Ticket findByProjection(Projection projection);
    Ticket findBySeat(Seat seat);
//    Ticket findTicketBySeatAndHallAndProjection(Seat seat, Hall hall, Projection projection);
    Ticket findTicketById(Long id);
//    List<Ticket> findByUser(User user);



}
