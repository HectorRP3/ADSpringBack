package com.hector.springboot.backend.jokes.models.dto;

import java.util.Set;

public class FlagsDTO {
	private Long id;
	private String flag;
	private Set<Long> jokesId;
	
	public FlagsDTO() {
	}
	
	
	public FlagsDTO(Long id, String flag, Set<Long> jokesId) {
		super();
		this.id = id;
		this.flag = flag;
		this.jokesId = jokesId;
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
	public Set<Long> getJokesId() {
		return jokesId;
	}
	public void setJokesId(Set<Long> jokesId) {
		this.jokesId = jokesId;
	}
	
	
	
}
