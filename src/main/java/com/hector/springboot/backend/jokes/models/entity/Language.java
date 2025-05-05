package com.hector.springboot.backend.jokes.models.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
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
import jakarta.validation.constraints.Pattern;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "language", catalog = "jokes")
public class Language implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Pattern(regexp = "^[a-z]{2}$", message = "El código de idioma debe tener el formato XX (dos letras minúsculas)")
	@Column(name = "code", length = 10)
	private String code;
	@Column(name = "language", length = 100)
	private String language;
	@JsonIgnoreProperties({ "language", "hibernateLazyInitializer", "handler" })
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "language",cascade = CascadeType.ALL)
	private Set<Jokes> jokeses = new HashSet<Jokes>(0);

	public Language() {
	}

	public Language(int id) {
		this.id = id;
	}

	public Language(int id, String code, String language, Set<Jokes> jokeses) {
		this.id = id;
		this.code = code;
		this.language = language;
		this.jokeses = jokeses;
	}


	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Set<Jokes> getJokeses() {
		return this.jokeses;
	}

	public void setJokeses(Set<Jokes> jokeses) {
		this.jokeses = jokeses;
	}
	
	 public void addJoke(Jokes joke) {
	        jokeses.add(joke);
	        joke.setLanguage(this);
	    }
	    public void removeJoke(Jokes joke) {
	        jokeses.remove(joke);
	        joke.setLanguage(null);
	    }

}