package com.hector.springboot.backend.jokes.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.hector.springboot.backend.jokes.models.entity.Telefonos;
import com.hector.springboot.backend.jokes.models.services.ITelefonosService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class TelefonoRestController {
	
	@Autowired
	private ITelefonosService telefonoService;
	
	@GetMapping("/telefonos")
	public List<Telefonos> index() {
		return telefonoService.findAll();
	}
	
	@GetMapping("/telefonos/{id}")
	public Telefonos show(@PathVariable Long id) {
		return this.telefonoService.findById(id);
	}
	@PostMapping("/telefonos")
	@ResponseStatus(HttpStatus.CREATED)
	public Telefonos create(@RequestBody Telefonos telefono) {
		this.telefonoService.save(telefono);
		return telefono;
	}
	@PutMapping("/telefonos/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Telefonos update(@RequestBody Telefonos telefono, @PathVariable Long id) {
		Telefonos currentTelefono = this.telefonoService.findById(id);
		currentTelefono.setNumero(telefono.getNumero());
		return currentTelefono;
	}
	
	@DeleteMapping("/telefonos/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		this.telefonoService.delete(id);
	}
	
}
