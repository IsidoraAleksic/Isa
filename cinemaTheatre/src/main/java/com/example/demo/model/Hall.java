package com.example.demo.model;

import java.util.List;

import javax.persistence.*;

@Entity(name = "halls")
public class Hall {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String name;

	private int rows;

	private int cols;

	@OneToMany
	private List<Seat> seats;

	@ManyToOne
	private CinemaTheater ct;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getCols() {
		return cols;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

	public CinemaTheater getCt() {
		return ct;
	}

	public void setCt(CinemaTheater ct) {
		this.ct = ct;
	}

	public Hall(Long id, String name, int rows, int cols, List<Seat> seats, CinemaTheater ct) {
		this.id = id;
		this.name = name;
		this.rows = rows;
		this.cols = cols;
		this.seats = seats;
		this.ct = ct;
	}

	public Hall() {

	}
}
