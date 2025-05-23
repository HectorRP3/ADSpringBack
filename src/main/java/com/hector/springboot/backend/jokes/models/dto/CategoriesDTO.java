package com.hector.springboot.backend.jokes.models.dto;

public class CategoriesDTO {
	private Integer id;
	private String category;

	public CategoriesDTO() {
	}

	public CategoriesDTO(Integer id, String category) {
		this.id = id;
		this.category = category;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
