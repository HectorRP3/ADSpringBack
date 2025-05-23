package com.hector.springboot.backend.jokes.models.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hector.springboot.backend.jokes.models.entity.PrimeraVez;
@JsonInclude(JsonInclude.Include.ALWAYS)
public class JokesDTO {

	private Long id;
	private String text1;
	private String text2;
	private String category;
	private String type;
	private String language;
	private Set<String> flags;
	private PrimeraVezDto primeraVez;
	
	public JokesDTO() {
	}

	public JokesDTO(Long id, String text1,String text2, String category, String type, String language,
			Set<String> flags, PrimeraVezDto primeraVez) {
		this.id = id;
		this.text1 = text1;
		this.text2 = text2;
		this.category = category;
		this.type = type;
		this.language = language;
		this.flags = flags;
		this.primeraVez = primeraVez;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText1() {
		return text1;
	}
	
	public void setText1(String text1) {
		this.text1 = text1;
	}
	
	public String getText2() {
		return text2;
	}
	
	public void setText2(String text2) {
		this.text2 = text2;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLanguage() {
		return language;
	}
	
	public void setLanguage(String language) {
		this.language = language;
	}

	public Set<String> getFlags() {
		return flags;
	}

	public void setFlags(Set<String> flags) {
		this.flags = flags;
	}
	
	public PrimeraVezDto getPrimeraVez() {
		return primeraVez;
	}

	public void setPrimeraVez(PrimeraVezDto primeraVez) {
		this.primeraVez = primeraVez;
	}

}
