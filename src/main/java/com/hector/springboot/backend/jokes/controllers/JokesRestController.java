package com.hector.springboot.backend.jokes.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hector.springboot.backend.jokes.models.dto.JokesDTO;
import com.hector.springboot.backend.jokes.models.entity.Jokes;
import com.hector.springboot.backend.jokes.models.services.IJokesService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class JokesRestController {
	@Autowired
	private IJokesService jokesService;
	
	@GetMapping("/jokes")
	public List<JokesDTO> index(){
		return jokesService.findAllDTO();
	}
}
