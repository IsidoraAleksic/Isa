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
	//@JoinColumn(name = "projection_id")
	private Projection projection;
	
	@ManyToOne
	//@JoinColumn(name = "ct_id")
	private CinemaTheater ct;
	
	@ManyToOne
	//@JoinColumn(name = "user_id")
	private User user;


	
}
