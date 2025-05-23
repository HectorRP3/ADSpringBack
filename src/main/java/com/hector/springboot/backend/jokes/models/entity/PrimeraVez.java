package com.hector.springboot.backend.jokes.models.entity;
// Generated 20 abr 2025, 18:12:31 by Hibernate Tools 4.3.6.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
/**
 * PrimeraVez generated by hbm2java
 */
@Entity
@Table(name = "primera_vez", catalog = "jokes", uniqueConstraints = @UniqueConstraint(columnNames = "idjoke"))
public class PrimeraVez implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private long id;
	@OneToOne(optional = false)
    @JoinColumn(name = "idjoke", unique = true)
	 @NotNull(message = "El chiste asociado (jokes) es obligatorio")
    private Jokes jokes;
	@Column(name = "programa", nullable = false)
	@NotBlank(message = "El nombre del programa es obligatorio")
    @Size(max = 100, message = "programa no puede superar los 100 caracteres")
	private String programa;
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_emision", nullable = false, length = 13)
	 @NotNull(message = "La fecha de emisión es obligatoria")
    @PastOrPresent(message = "La fecha de emisión no puede ser futura")
	private Date fechaEmision;
	@NotNull(message = "Los teléfonos son obligatorios")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "primeraVez")
	private Set<Telefonos> telefonoses = new HashSet<Telefonos>(0);

	public PrimeraVez() {
	}

	public PrimeraVez(long id, String programa, Date fechaEmision) {
		this.id = id;
		this.programa = programa;
		this.fechaEmision = fechaEmision;
	}

	public PrimeraVez(long id, Jokes jokes, String programa, Date fechaEmision, Set<Telefonos> telefonoses) {
		this.id = id;
		this.jokes = jokes;
		this.programa = programa;
		this.fechaEmision = fechaEmision;
		this.telefonoses = telefonoses;
	}

	
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public Jokes getJokes() {
		return this.jokes;
	}

	public void setJokes(Jokes jokes) {
		this.jokes = jokes;
	}

	public String getPrograma() {
		return this.programa;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
	}

	
	public Date getFechaEmision() {
		return this.fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public Set<Telefonos> getTelefonoses() {
		return this.telefonoses;
	}

	public void setTelefonoses(Set<Telefonos> telefonoses) {
		this.telefonoses = telefonoses;
	}

}
