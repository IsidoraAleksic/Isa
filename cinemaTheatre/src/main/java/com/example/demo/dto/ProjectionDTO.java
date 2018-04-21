package com.example.demo.dto;

import java.sql.Date;
import java.sql.Time;

public class ProjectionDTO {

	@Override
	public String toString() {
		return "ProjectionDTO [id=" + id + ", name=" + name + ", actors=" + actors + ", genre=" + genre + ", director="
				+ director + ", duration=" + duration + ", imagePath=" + imagePath + ", description=" + description
				+ ", date=" + date + ", time=" + time + ", hall_id=" + hall_id + ", ct_id=" + ct_id + ", price=" + price
				+ ", hall_name=" + hall_name + "]";
	}

	private long id;

	private String name;

	private String actors;

	private String genre;

	private String director;

	private int duration;

	private String imagePath;

	private String description;

	private String date;

	private String time;

	private long hall_id;

	private long ct_id;

	private float price;

	private String hall_name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public long getHall_id() {
		return hall_id;
	}

	public void setHall_id(long hall_id) {
		this.hall_id = hall_id;
	}

	public String getHall_name() {
		return hall_name;
	}

	public void setHall_name(String hall_name) {
		this.hall_name = hall_name;
	}

	public ProjectionDTO() {

	}

	public long getCt_id() {
		return ct_id;
	}

	public void setCt_id(long ct_id) {
		this.ct_id = ct_id;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public ProjectionDTO(long id, String name, String actors, String genre, String director, int duration,
			String imagePath, String description, String date, String time, long hall_id, long ct_id, float price,
			String hall_name) {
		this.id = id;
		this.name = name;
		this.actors = actors;
		this.genre = genre;
		this.director = director;
		this.duration = duration;
		this.imagePath = imagePath;
		this.description = description;
		this.date = date;
		this.time = time;
		this.hall_id = hall_id;
		this.ct_id = ct_id;
		this.price = price;
		this.hall_name = hall_name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

}
