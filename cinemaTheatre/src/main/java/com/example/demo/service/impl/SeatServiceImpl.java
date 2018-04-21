package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Seat;
import com.example.demo.repository.SeatRepository;
import com.example.demo.service.SeatService;

@Service
public class SeatServiceImpl implements SeatService{

	@Autowired
	private SeatRepository seatRepository;
	
	@Override
	public Seat addSeat(Seat seat) {
		return seatRepository.save(seat);
	}

}
