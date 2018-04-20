package com.example.demo.dto;

import javax.validation.constraints.*;

import com.example.demo.model.CinemaTheater;

public class HallDTO {

	private Long id;
	
	private String name;

	private int rows;
	
	private int cols;
	
	private Long ctId;
	

	public HallDTO() {

	}

	public HallDTO(Long id, String name, int rows, int cols, Long ctId, CinemaTheater ct) {
		this.id = id;
		this.name = name;
		this.rows = rows;
		this.cols = cols;
		this.ctId = ctId;
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

	@Override
	public String toString() {
		return "HallDTO [id=" + id + ", name=" + name + ", rows=" + rows + ", cols=" + cols + ", ctId=" + ctId + "]";
	}

}
