package com.hector.springboot.backend.jokes.controllers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

import com.hector.springboot.backend.jokes.mapper.PrimeraVezMapper;
import com.hector.springboot.backend.jokes.models.dto.PrimeraVezDto;
import com.hector.springboot.backend.jokes.models.entity.Jokes;
import com.hector.springboot.backend.jokes.models.entity.PrimeraVez;
import com.hector.springboot.backend.jokes.models.entity.Telefonos;
import com.hector.springboot.backend.jokes.models.services.IJokesService;
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
	@Autowired
	private IJokesService jokesService;
	@Autowired
	private PrimeraVezMapper primeraVezMapper;
	
	@GetMapping("/primeravez")
	public List<PrimeraVezDto> index() {
		return primeraVezService.findAll().stream().map(primeraVezMapper::toDTO).toList();
	}

	@GetMapping("/primeravez/{id}")
	public PrimeraVez show(@PathVariable Long id) {
		return this.primeraVezService.findById(id);
	}

	@PostMapping("/primeravez")
	@ResponseStatus(HttpStatus.CREATED)
	public PrimeraVezDto create(@RequestBody PrimeraVezDto primeraVez) {
		Set<String> telefonos = primeraVez.getTelefonoses();
		PrimeraVez primeraVezEntity = new PrimeraVez();
		primeraVezEntity.setPrograma(primeraVez.getPrograma());
		primeraVezEntity.setFechaEmision(primeraVez.getFechaEmision());
		Jokes jokesEntity = this.jokesService.findById(primeraVez.getJokes());
		primeraVezEntity.setJokes(jokesEntity);
		this.primeraVezService.save(primeraVezEntity);
		for (String telefono : telefonos) {
			Telefonos telefonoEntity = new Telefonos();
			telefonoEntity.setNumero(telefono);
			telefonoEntity.setPrimeraVez(primeraVezEntity);
			this.telefonoService.save(telefonoEntity);
		}
		
		PrimeraVezDto primeraVezDto = new PrimeraVezDto();
		primeraVezDto.setId(primeraVezEntity.getId());
		primeraVezDto.setPrograma(primeraVezEntity.getPrograma());
		primeraVezDto.setFechaEmision(primeraVezEntity.getFechaEmision());
		primeraVezDto.setJokes(primeraVezEntity.getJokes().getId());
		primeraVezDto.setTelefonoses(telefonos);
		return primeraVezDto;
	}

	@PutMapping("/primeravez/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public PrimeraVezDto update(@RequestBody PrimeraVezDto primeraVez, @PathVariable Long id) {
		/*PrimeraVez currentPrimeraVez = this.primeraVezService.findById(id);
		currentPrimeraVez.setPrograma(primeraVez.getPrograma());
		currentPrimeraVez.setFechaEmision(primeraVez.getFechaEmision());
		currentPrimeraVez.setJokes(primeraVez.getJokes());
		return currentPrimeraVez;*/
		PrimeraVez currentPrimeraVez = this.primeraVezService.findById(id);
		currentPrimeraVez.setPrograma(primeraVez.getPrograma());
		currentPrimeraVez.setFechaEmision(primeraVez.getFechaEmision());
		currentPrimeraVez.setJokes(this.jokesService.findById(primeraVez.getJokes()));
		Set<String> telefonos = primeraVez.getTelefonoses();
		this.telefonoService.deleteAllWithPrimeraVezById(currentPrimeraVez.getId());
		for (String telefono : telefonos) {
			Telefonos telefonoEntity = new Telefonos();
			telefonoEntity.setNumero(telefono);
			telefonoEntity.setPrimeraVez(currentPrimeraVez);
			this.telefonoService.save(telefonoEntity);
		}
		this.primeraVezService.save(currentPrimeraVez);
		PrimeraVezDto primeraVezDto = new PrimeraVezDto();
		primeraVezDto.setId(currentPrimeraVez.getId());
		primeraVezDto.setPrograma(currentPrimeraVez.getPrograma());
		primeraVezDto.setFechaEmision(currentPrimeraVez.getFechaEmision());
		primeraVezDto.setJokes(currentPrimeraVez.getJokes().getId());
		primeraVezDto.setTelefonoses(telefonos);
		return primeraVezDto;
	}

	@DeleteMapping("/primeravez/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		this.telefonoService.deleteAllWithPrimeraVezById(id);
		this.primeraVezService.delete(id);
	}
	

}
