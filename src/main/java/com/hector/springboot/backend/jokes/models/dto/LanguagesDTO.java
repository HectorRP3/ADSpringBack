package com.hector.springboot.backend.jokes.models.dto;

public class LanguagesDTO {
	private Integer id;
	private String code;
	private String language;

	public LanguagesDTO() {
	}

	public LanguagesDTO(Integer id,String code, String language) {
		this.id = id;
		this.code = code;
		this.language = language;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
