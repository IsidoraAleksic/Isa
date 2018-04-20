package com.example.demo.dto;

import com.example.demo.model.SeatType;

public class SeatDTO {

	private long id;

	private String hall_name;

	private String time;

	private int row;

	private int col;

	private float price;

	private String movie_name;

	public SeatDTO(long id, String hall_name, String time, int row, int col, float price, String movie_name) {
		this.id = id;
		this.hall_name = hall_name;
		this.time = time;
		this.row = row;
		this.col = col;
		this.price = price;
		this.movie_name = movie_name;
	}

	public SeatDTO() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getHall_name() {
		return hall_name;
	}

	public void setHall_name(String hall_name) {
		this.hall_name = hall_name;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
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

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getMovie_name() {
		return movie_name;
	}

	public void setMovie_name(String movie_name) {
		this.movie_name = movie_name;
	}

}
