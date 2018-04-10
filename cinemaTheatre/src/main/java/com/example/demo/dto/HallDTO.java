package com.example.demo.dto;

import javax.validation.constraints.*;

import com.example.demo.model.CinemaTheater;

public class HallDTO {

	private Long id;
	
	@NotNull
	@Size(min=1)
	private String name;

	@NotNull
	private int rows;
	
	@NotNull
	private int cols;
	
	@NotNull
	private Long ctId;
	
	private CinemaTheater ct;

	public HallDTO() {

	}

	public HallDTO(Long id, String name, int rows, int cols, Long ctId, CinemaTheater ct) {
		this.id = id;
		this.name = name;
		this.rows = rows;
		this.cols = cols;
		this.ctId = ctId;
		this.ct = ct;
	}

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

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getCols() {
		return cols;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}

	public Long getCtId() {
		return ctId;
	}

	public void setCtId(Long ctId) {
		this.ctId = ctId;
	}

	public CinemaTheater getCt() {
		return ct;
	}

	public void setCt(CinemaTheater ct) {
		this.ct = ct;
	}

}
