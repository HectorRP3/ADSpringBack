package com.hector.springboot.backend.jokes.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hector.springboot.backend.jokes.mapper.JokesMapper;
import com.hector.springboot.backend.jokes.models.dto.JokesDTO;
import com.hector.springboot.backend.jokes.models.entity.Jokes;
import com.hector.springboot.backend.jokes.models.services.IJokesService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class JokesRestController {
	@Autowired
	private IJokesService jokesService;
	
	@Autowired
	private JokesMapper jokesMapper;

	
	@GetMapping("/jokes")
	public List<JokesDTO> index(){
		return jokesService.findAllDTO().stream().sorted((j1, j2) -> j1.getId().compareTo(j2.getId())).toList();
	}
	
	@GetMapping("/jokes/{id}")
	public ResponseEntity<?> show(@PathVariable Long id){
		JokesDTO jokesDTO = null;
		Jokes jokes = null;
		Map<String,Object> response = new HashMap<>();
		try {
			jokes = jokesService.findById(id);
			jokesDTO = jokesMapper.toDTO(jokes);
	    } catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
		if (jokesDTO == null) {
			response.put("mensaje", "El chiste ID: ".concat(id.toString()).concat(" no existe en la base de datos!"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<JokesDTO>(jokesDTO, HttpStatus.OK);
	}

	
}

