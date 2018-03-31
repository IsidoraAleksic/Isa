package com.example.demo.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="ct")
public class CinemaTheater {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(name = "name", columnDefinition = "VARCHAR(60)")
	@Size(min = 2, max = 60)
	private String name;

	@NotNull
	@Column(name = "address", columnDefinition = "VARCHAR(120)")
	@Size(min = 10, max = 120)
	private String address;

	@Size(max = 280)
	@Column(name = "description", columnDefinition = "VARCHAR(280)")
	private String description;

	@Enumerated(EnumType.STRING)
	@Column(name="type")
	private CTType type;
	
	/**
	 * TODO: OCENA AMBIJENTA, SALE, PROJEKCIJE, FILMOVI
	 */

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CinemaTheater() {

	}

	public CinemaTheater(Long id, String name, String address, String description) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.description = description;
	}

}
