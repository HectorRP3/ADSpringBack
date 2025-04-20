package com.hector.springboot.backend.jokes.models.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hector.springboot.backend.jokes.models.entity.PrimeraVez;

@Service
public interface IPrimeraVezService {
	
	public List<PrimeraVez> findAll();
	public PrimeraVez findById(Long id);
	public PrimeraVez save(PrimeraVez primeraVez);
	public void delete(Long id);
	
}
