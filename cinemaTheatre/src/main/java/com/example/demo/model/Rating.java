package com.example.demo.model;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity(name="ratings")
public class Rating {

	@Id
	@GeneratedValue
	private long id;

	@NotNull
	@Min(1)
	@Max(5)
	private short projectionGrade;
	@NotNull
	@Min(1)
	@Max(5)
	private short ambientGrade;
	
	@ManyToOne
	private Projection projection;
	
	@ManyToOne
	private CinemaTheater ct;
	
	@ManyToOne
	private User user;
	
}
