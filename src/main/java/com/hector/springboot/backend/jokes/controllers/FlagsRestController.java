package com.hector.springboot.backend.jokes.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hector.springboot.backend.jokes.mapper.FlagsMapper;
import com.hector.springboot.backend.jokes.models.dto.FlagsDTO;
import com.hector.springboot.backend.jokes.models.entity.Flags;
import com.hector.springboot.backend.jokes.models.entity.Jokes;
import com.hector.springboot.backend.jokes.models.services.IFlagsService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class FlagsRestController {
	@Autowired
	private IFlagsService flagsService;
	@Autowired
	private FlagsMapper flagsMapper;
	
	@GetMapping("/flags")
	public List<FlagsDTO> index(){
		return flagsService.findAllDTO().stream().sorted((f1, f2) -> f1.getId().compareTo(f2.getId()))
				.toList();
	}
	
	@GetMapping("/flags/{id}")
	public  ResponseEntity<?> show(@PathVariable Long id) {
		FlagsDTO flagsDTO = null;
		Map<String,Object> response = new HashMap<>();
		try {
			flagsDTO = flagsService.findByIdDTO(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (flagsDTO == null) {
			response.put("mensaje", "La bandera ID: ".concat(id.toString()).concat(" no existe en la base de datos!"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<FlagsDTO>(flagsDTO, HttpStatus.OK);
	}
	@PostMapping("/flags")
	public ResponseEntity<?> create(@Valid @RequestBody Flags flags,BindingResult result) {
		Flags flagsNew = null;
		Map<String, Object> response = new HashMap<>();
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err->"El campo '"+err.getField()+"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
		}
		try {
			flagsNew = flagsService.save(flags);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		FlagsDTO flagsDTO = flagsMapper.toFlagsDTO(flagsNew);
		response.put("mensaje", "La bandera ha sido creada con éxito!");
		response.put("bandera", flagsDTO);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	@PutMapping("/flags/{id}")
	public ResponseEntity<?> update(@Valid @PathVariable Long id, @RequestBody Flags flags,BindingResult result) {
		Flags currentFlags = flagsService.findById(id);
		Flags flagsUpdate = null;
		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		if (currentFlags == null) {
			response.put("mensaje", "Error: no se pudo editar, la bandera ID: ".concat(id.toString())
					.concat(" no existe en la base de datos"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			currentFlags.setFlag(flags.getFlag());
			Set<Jokes> listajokes = new HashSet<Jokes>(0);
			listajokes.addAll(currentFlags.getJokeses());
			listajokes.addAll(flags.getJokeses());
			currentFlags.setJokeses(listajokes);
			flagsUpdate = flagsService.save(currentFlags);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar la bandera en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} 
		FlagsDTO flagsDTO =flagsMapper.toFlagsDTO(flagsUpdate) ;
		response.put("mensaje", "La bandera ha sido actualizada con éxito!");
		response.put("bandera", flagsDTO);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/flags/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			flagsService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar la bandera en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La bandera ha sido eliminada con éxito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}


