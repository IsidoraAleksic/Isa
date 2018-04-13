package com.example.demo.model;

import javax.persistence.*;

@Entity(name="reservations")
public class Reservation {

	@Id
	@GeneratedValue
	private Long id;
	
	private short discount;
	
	@ManyToOne
	private Projection projection;

	public short getDiscount() {
		return discount;
	}

	public void setDiscount(short discount) {
		this.discount = discount;
	}

	public Projection getProjection() {
		return projection;
	}

	public void setProjection(Projection projection) {
		this.projection = projection;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Reservation(Long id) {
		this.id = id;
	}
	
}
