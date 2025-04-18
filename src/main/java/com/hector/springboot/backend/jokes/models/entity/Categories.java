package com.hector.springboot.backend.jokes.models.entity;
// Generated 13 abr 2025, 11:03:08 by Hibernate Tools 4.3.6.Final

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
@Table(name = "categories", catalog = "jokes")
public class Categories implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private int id;
	private String category;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "categories",orphanRemoval = true)
	private Set<Jokes> jokeses = new HashSet<Jokes> (0);

	public Categories() {
	}

	public Categories(int id, String category) {
		this.id = id;
		this.category = category;
	}

	public Categories(int id, String category, Set<Jokes>  jokeses) {
		this.id = id;
		this.category = category;
		this.jokeses = jokeses;
	}


	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Set<Jokes>  getJokeses() {
		return this.jokeses;
	}

	public void setJokeses(Set<Jokes>  jokeses) {
		this.jokeses = jokeses;
	}

}