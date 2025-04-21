package com.hector.springboot.backend.jokes.models.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hector.springboot.backend.jokes.models.dto.PrimeraVezDto;
import com.hector.springboot.backend.jokes.models.entity.Telefonos;

@Service
public interface ITelefonosService {
	
	public List<Telefonos> findAll();
	public Telefonos findById(Long id);
	public Telefonos save(Telefonos telefono);
	public void delete(Long id);
	void deleteAllWithPrimeraVezById(Long id);
}
