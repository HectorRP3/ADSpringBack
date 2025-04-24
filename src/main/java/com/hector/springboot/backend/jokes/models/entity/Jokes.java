package com.hector.springboot.backend.jokes.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
@Table(name = "jokes")
public class Jokes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	@NotNull(message = "La categoría es obligatoria")
	private Categories categories;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "language_id")
    @NotNull(message = "El idioma es obligatorio")
	private Language language;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type_id")
    @NotNull(message = "El tipo es obligatorio")
	private Types types;
	@Column(name = "text1", length = 1000)
	 @NotBlank(message = "El texto principal es obligatorio")
    @Size(max = 1000, message = "text1 no puede superar los 1000 caracteres")
	private String text1;
	@Column(name = "text2", length = 1000)
    @Size(max = 1000, message = "text2 no puede superar los 1000 caracteres")
	private String text2;
	@ManyToMany(fetch = FetchType.LAZY,cascade= {CascadeType.MERGE})
	@JoinTable(name = "jokes_flags", catalog = "jokes", joinColumns = {
			@JoinColumn(name = "joke_id") }, inverseJoinColumns = {
					@JoinColumn(name = "flag_id") })
	private Set<Flags> flagses = new HashSet<Flags>(0);
    @OneToOne(mappedBy = "jokes", fetch = FetchType.LAZY)
	 private PrimeraVez primeraVez;


	public Jokes() {
	}

	public Jokes(Long id) {
		this.id = id;
	}

	public Jokes(Long id, Categories categories, Language language, Types types, String text1, String text2,
			Set<Flags> flagses,PrimeraVez primeraVezs) {
		this.id = id;
		this.categories = categories;
		this.language = language;
		this.types = types;
		this.text1 = text1;
		this.text2 = text2;
		this.flagses = flagses;
		this.primeraVez = primeraVezs;
	}

	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Categories getCategories() {
		return this.categories;
	}

	public void setCategories(Categories categories) {
		this.categories = categories;
	}

	
	public Language getLanguage() {
		return this.language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	
	public Types getTypes() {
		return this.types;
	}

	public void setTypes(Types types) {
		this.types = types;
	}

	public String getText1() {
		return this.text1;
	}

	public void setText1(String text1) {
		this.text1 = text1;
	}

	public String getText2() {
		return this.text2;
	}

	public void setText2(String text2) {
		this.text2 = text2;
	}

	
	public Set<Flags> getFlagses() {
		return this.flagses;
	}

	public void setFlagses(Set<Flags> flagses) {
		this.flagses = flagses;
	}
	
	public PrimeraVez getPrimeraVez() {
		return this.primeraVez;
	}

	public void setPrimeraVez(PrimeraVez primeraVez) {
		this.primeraVez = primeraVez;
	}
	
	@Override
	public String toString() {
		return "Jokes [id=" + id + ", categories=" + categories + ", language=" + language + ", types=" + types
				+ ", text1=" + text1 + ", text2=" + text2 + "]";
	}
}