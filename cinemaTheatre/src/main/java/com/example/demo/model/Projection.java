package com.example.demo.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity(name = "projections")
public class Projection {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	private String name;

	@NotNull
	private String actors;

	@NotNull
	private String genre;

	@NotNull
	private String director;

	@NotNull
	private int duration;

	@NotNull
	private String imagePath;

	@NotNull
	@Size(max = 280)
	private String description;

	@ManyToOne
	private Hall hall;

	@ManyToOne
	private CinemaTheater ct;

	@Column(nullable = false)
	private Date date;

	@Column(nullable = false)
	private float price;

	@ManyToMany
	private List<Seat> taken;

	@OneToMany
	private List<Reservation> reserved;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getActors() {
		return actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Projection(Long id, String name, String actors, String genre, String director, int duration,
			String imagePath, String description, Hall hall, Date date, Float price, List<Seat> taken,
			List<Reservation> reserved) {
		this.id = id;
		this.name = name;
		this.actors = actors;
		this.genre = genre;
		this.director = director;
		this.duration = duration;
		this.imagePath = imagePath;
		this.description = description;
		this.hall = hall;
		this.date = date;
		this.price = price;
		this.taken = taken;
		this.reserved = reserved;
	}

	public Projection() {

	}

}
