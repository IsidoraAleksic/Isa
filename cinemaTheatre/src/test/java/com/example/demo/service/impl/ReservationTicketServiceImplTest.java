package com.example.demo.service.impl;

import com.example.demo.model.Seat;
import com.example.demo.model.SeatType;
import com.example.demo.model.Ticket;
import com.example.demo.repository.ReservationTicketRepository;
import com.example.demo.repository.SeatRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationTicketServiceImplTest {

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private ReservationTicketRepository reservationTicketRepository;

    @Test
    public void save() {
        Seat seat= new Seat();
        int count = seatRepository.findAll().size();
        seat.setSeatType(SeatType.AVAILABLE);
        seat.setId(25L);
        seat.setCol(3);
        seat.setRow(8);
        seatRepository.save(seat);
        int countAfter = seatRepository.findAll().size();
        assertThat(count + 1).isEqualTo(countAfter);
    }

    @Test
    public void save1() {
        Ticket t = new Ticket(1L);
        int count = reservationTicketRepository.findAll().size();
        reservationTicketRepository.save(t);
        int countAfter = reservationTicketRepository.findAll().size();
        assertThat(count + 1).isEqualTo(countAfter);


    }



    @Test
    public void findByProjection() {
    }

    @Test
    public void findByProjectionAndHall() {
    }


    @Test
    public void findTicketById() {
        Ticket ticket = reservationTicketRepository.findTicketById(1L);
    }

    @Test
    public void findAll() {
    }

    @Test
    public void findSeatById() {
    }

    @Test
    public void findByHall() {
    }

    @Test
    public void delete() {
    }




}