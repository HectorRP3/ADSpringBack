package com.hector.springboot.backend.jokes.models.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hector.springboot.backend.jokes.models.entity.Jokes;
import com.hector.springboot.backend.jokes.models.entity.Telefonos;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

public class PrimeraVezDto {
	private long id;
	private Long jokes;
	private String programa;
	private Date fechaEmision;

	private Set<String> telefonoses;

	public PrimeraVezDto() {
	}

	public PrimeraVezDto(long id, Long jokes, Date fechaEmision,String programa) {
		this.id = id;
		this.jokes = jokes;
		this.fechaEmision = fechaEmision;
		this.programa = programa;
	}

	public PrimeraVezDto(long id, Long jokes, Date fechaEmision, Set<String> telefonoses,String programa) {
		this.id = id;
		this.jokes = jokes;
		this.fechaEmision = fechaEmision;
		this.telefonoses = telefonoses;
		this.programa = programa;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getJokes() {
		return this.jokes;
	}

	public void setJokes(Long jokes) {
		this.jokes = jokes;
	}

	public Date getFechaEmision() {
		return this.fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public Set<String> getTelefonoses() {
		return this.telefonoses;
	}

	public void setTelefonoses(Set<String> telefonoses) {
		this.telefonoses = telefonoses;
	}
	
	public String getPrograma() {
		return programa;
	}
	
	public void setPrograma(String programa) {
		this.programa = programa;
	}
	@Override
	public String toString() {
		return "PrimeraVezDto [id=" + id + ", jokes=" + jokes + ", fechaEmision=" + fechaEmision + ", telefonoses="
				+ telefonoses + "]";
	}
	
}
