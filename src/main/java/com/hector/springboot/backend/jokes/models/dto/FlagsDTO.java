package com.hector.springboot.backend.jokes.models.dto;

import java.util.Set;

public class FlagsDTO {
	private Long id;
	private String flag;
	private Set<Long> jokeses;
	
	public FlagsDTO() {
	}
	
	
	public FlagsDTO(Long id, String flag, Set<Long> jokeses) {
		super();
		this.id = id;
		this.flag = flag;
		this.jokeses = jokeses;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public Set<Long> getJokeses() {
		return jokeses;
	}
	public void setJokeses(Set<Long> jokeses) {
		this.jokeses = jokeses;
	}
	
	
	
}
