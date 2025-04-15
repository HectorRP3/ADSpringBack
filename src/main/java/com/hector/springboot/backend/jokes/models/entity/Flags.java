package com.hector.springboot.backend.jokes.models.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.JoinColumn;
@Entity
@Table(name = "flags", catalog = "jokes")
public class Flags implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "flag", length = 100)
	private String flag;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "jokes_flags", catalog = "jokes", joinColumns = {
			@JoinColumn(name = "flag_id") }, inverseJoinColumns = {
					@JoinColumn(name = "joke_id") })
	private Set<Jokes> jokeses = new HashSet<Jokes>(0);

	public Flags() {
	}

	public Flags(Long id, String flag) {
		this.id = id;
		this.flag = flag;
	}

	public Flags(Long id, String flag, Set<Jokes> jokeses) {
		this.id = id;
		this.flag = flag;
		//this.jokeses = jokeses;
	}
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	
	public Set<Jokes> getJokeses() {
		return this.jokeses;
	}

	public void setJokeses(Set<Jokes> jokeses) {
		this.jokeses = jokeses;
	}

}
