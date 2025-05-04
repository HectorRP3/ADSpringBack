package com.hector.springboot.backend.jokes.models.dto;

public class TypesDTO {
	private Integer id;
	private String type;

	public TypesDTO() {
	}

	public TypesDTO(Integer id, String type) {
		this.id = id;
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
