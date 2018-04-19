package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "ct")
public class CinemaTheater {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	@Column(name = "name", columnDefinition = "VARCHAR(60)")
	@Size(min = 2, max = 60)
	private String name;

	@NotNull
	@Column(name = "address", columnDefinition = "VARCHAR(120)")
	@Size(max = 120)
	private String address;

	@Size(max = 280)
	@Column(name = "description", columnDefinition = "VARCHAR(280)")
	private String description;

	@Enumerated(EnumType.STRING)
	@Column(name = "type", columnDefinition = "VARCHAR(20)")
	private CTType type;

	@OneToMany
	@JsonIgnore
	private List<Projection> projections;

	@OneToMany
	@JsonIgnore
	private List<Hall> halls;

	@Column(name = "ambient")
	private float ambient;

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

	public CinemaTheater(long id, @NotNull @Size(min = 2, max = 60) String name,
			@NotNull @Size(max = 120) String address, @Size(max = 280) String description, CTType type,
			float ambient) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.description = description;
		this.type = type;
		this.ambient = ambient;
	}

	public CTType getType() {
		return type;
	}

	public void setType(CTType type) {
		this.type = type;
	}

	public List<Projection> getProjections() {
		return projections;
	}

	public void setProjections(List<Projection> projections) {
		this.projections = projections;
	}

	public List<Hall> getHalls() {
		return halls;
	}

	public void setHalls(List<Hall> halls) {
		this.halls = halls;
	}

	public float getAmbient() {
		return ambient;
	}

	public void setAmbient(float ambient) {
		this.ambient = ambient;
	}

}
