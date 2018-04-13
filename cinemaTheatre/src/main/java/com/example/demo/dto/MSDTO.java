package com.example.demo.dto;

public class MSDTO {

	private long id;
	private String name;
	private String director;
	private String description;
	private short duration;
	private String genre;
	private String imagePath;
	private String actors;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public short getDuration() {
		return duration;
	}
	public void setDuration(short duration) {
		this.duration = duration;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getActors() {
		return actors;
	}
	public void setActors(String actors) {
		this.actors = actors;
	}
	public MSDTO(long id, String name, String director, String description, short duration, String genre,
			String imagePath, String actors) {
		super();
		this.id = id;
		this.name = name;
		this.director = director;
		this.description = description;
		this.duration = duration;
		this.genre = genre;
		this.imagePath = imagePath;
		this.actors = actors;
	}
	
	public MSDTO() {
		
	}
	
}
