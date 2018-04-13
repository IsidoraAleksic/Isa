package com.example.demo.dto;

public class CTDTO {

	private Long id;
	private String name;
	private String address;
	private String description;
	private Float ambient;
	
	public Float getAmbient() {
		return ambient;
	}

	public void setAmbient(Float ambient) {
		this.ambient = ambient;
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

	public CTDTO() {

	}

	public CTDTO(Long id, String name, String address, String description, Float ambient) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.description = description;
		this.ambient = ambient;
	}
	
}
