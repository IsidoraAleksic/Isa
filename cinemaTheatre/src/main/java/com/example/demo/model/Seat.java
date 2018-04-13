package com.example.demo.model;

import javax.persistence.*;

@Entity(name="seats")
public class Seat {

	@Id
	@GeneratedValue
	private Long id;
	
	private int row;
	
	private int col;
	
	@Enumerated(EnumType.STRING)
	private SeatType seatType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public Seat(Long id, int row, int col, SeatType seatType) {
		this.id = id;
		this.row = row;
		this.col = col;
		this.seatType = seatType;
	}
	
	public Seat() {
		
	}

	public SeatType getSeatType() {
		return seatType;
	}

	public void setSeatType(SeatType seatType) {
		this.seatType = seatType;
	}

	
	
}
