package com.example.demo.model;

import java.util.List;

import javax.persistence.*;

@Entity(name="ms")
public class MovieShow {

	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	@OneToMany
	private List<Actor> actors;
	
	private String genre;
	
	private String director;
	
	private int duration;
	
	private String imagePath;
	
	private String description;

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

	public List<Actor> getActors() {
		return actors;
	}

	public void setActors(List<Actor> actors) {
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

	public MovieShow(Long id, String name, List<Actor> actors, String genre, String director, int duration,
			String imagePath, String description) {
		this.id = id;
		this.name = name;
		this.actors = actors;
		this.genre = genre;
		this.director = director;
		this.duration = duration;
		this.imagePath = imagePath;
		this.description = description;
	}

	public MovieShow() {
	}
	
	//XXX: Pitanje 4
	
	
	
}
