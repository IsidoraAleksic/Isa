package com.example.demo.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.*;

@Entity(name = "projections")
public class Projection {

	@Id
	@GeneratedValue
	private Long id;

	@OneToOne
	private MovieShow happening;

	@ManyToOne
	private Hall hall;

	@Column(nullable = false)
	private Date date;

	@Column(nullable = false)
	private Float price;

	@ManyToMany
	private List<Seat> taken;

	@OneToMany
	private List<Reservation> reserved;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MovieShow getHappening() {
		return happening;
	}

	public void setHappening(MovieShow happening) {
		this.happening = happening;
	}

	public Hall getHall() {
		return hall;
	}

	public void setHall(Hall hall) {
		this.hall = hall;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public List<Seat> getTaken() {
		return taken;
	}

	public void setTaken(List<Seat> taken) {
		this.taken = taken;
	}

	public List<Reservation> getReserved() {
		return reserved;
	}

	public void setReserved(List<Reservation> reserved) {
		this.reserved = reserved;
	}

	public Projection(Long id, MovieShow happening, Hall hall, Date date, Float price, List<Seat> taken,
			List<Reservation> reserved) {
		this.id = id;
		this.happening = happening;
		this.hall = hall;
		this.date = date;
		this.price = price;
		this.taken = taken;
		this.reserved = reserved;
	}

	public Projection() {

	}

}
