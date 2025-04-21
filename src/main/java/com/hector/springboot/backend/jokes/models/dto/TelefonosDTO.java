package com.hector.springboot.backend.jokes.models.dto;

public class TelefonosDTO {
	public Long id;
	public String numero;
	public Long primeraVezId;
	
	public TelefonosDTO() {
	}
	
	public TelefonosDTO(Long id, String numero, Long primeraVezId) {
		super();
		this.id = id;
		this.numero = numero;
		this.primeraVezId = primeraVezId;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNumero() {
		return numero;
	}
	
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public Long getPrimeraVezId() {
		return primeraVezId;
	}
	
	public void setPrimeraVezId(Long primeraVezId) {
		this.primeraVezId = primeraVezId;
	}
	
	@Override
	public String toString() {
		return "TelefonosDTO [id=" + id + ", numero=" + numero + ", primeraVezId=" + primeraVezId + "]";
	}

}
