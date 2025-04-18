package com.hector.springboot.backend.jokes.models.entity;

import java.util.HashSet;
import java.util.Set;
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
@Table(name = "types", catalog = "jokes")
public class Types implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private int id;
	@Column(name = "type", length = 100)
	private String type;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "types", orphanRemoval = true)
	private Set<Jokes> jokeses = new HashSet<Jokes>(0);

	public Types() {
	}

	public Types(int id, String type) {
		this.id = id;
		this.type = type;
	}

	public Types(int id, String type, Set<Jokes> jokeses) {
		this.id = id;
		this.type = type;
		this.jokeses = jokeses;
	}


	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<Jokes> getJokeses() {
		return this.jokeses;
	}

	public void setJokeses(Set<Jokes> jokeses) {
		this.jokeses = jokeses;
	}

}