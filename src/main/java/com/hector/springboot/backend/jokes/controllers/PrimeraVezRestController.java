package com.hector.springboot.backend.jokes.controllers;

import java.util.List;
import java.util.Set;

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

import com.hector.springboot.backend.jokes.models.entity.PrimeraVez;
import com.hector.springboot.backend.jokes.models.entity.Telefonos;
import com.hector.springboot.backend.jokes.models.services.IPrimeraVezService;
import com.hector.springboot.backend.jokes.models.services.ITelefonosService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class PrimeraVezRestController {
	
	@Autowired
	private IPrimeraVezService primeraVezService;
	@Autowired
	private ITelefonosService telefonoService;
	
	@GetMapping("/primeravez")
	public List<PrimeraVez> index() {
		return primeraVezService.findAll();
	}

	@GetMapping("/primeravez/{id}")
	public PrimeraVez show(@PathVariable Long id) {
		return this.primeraVezService.findById(id);
	}

	@PostMapping("/primeravez")
	@ResponseStatus(HttpStatus.CREATED)
	public PrimeraVez create(@RequestBody PrimeraVez primeraVez) {
		this.primeraVezService.save(primeraVez);
		return primeraVez;
	}

	@PutMapping("/primeravez/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public PrimeraVez update(@RequestBody PrimeraVez primeraVez, @PathVariable Long id) {
		PrimeraVez currentPrimeraVez = this.primeraVezService.findById(id);
		currentPrimeraVez.setPrograma(primeraVez.getPrograma());
		currentPrimeraVez.setFechaEmision(primeraVez.getFechaEmision());
		currentPrimeraVez.setJokes(primeraVez.getJokes());
		return currentPrimeraVez;
	}

	@DeleteMapping("/primeravez/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		this.primeraVezService.delete(id);
	}
	

}
