package com.example.demo.dao;

import com.example.demo.model.Projection;
import com.example.demo.model.Seat;
import com.example.demo.model.Ticket;
import com.example.demo.model.User;

public interface TicketsDao {

    public boolean create(short discount, int status, Seat seat, Projection projection);
}
