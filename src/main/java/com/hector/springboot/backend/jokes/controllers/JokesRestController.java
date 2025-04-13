package com.hector.springboot.backend.jokes.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity index(){
		
		System.out.println("JokesRestController index() called" + jokesService.findAllDTO().size());
		int i = jokesService.findAllDTO().size();
		System.out.println("JokesRestController index() called" + i);
		//return jokesService.findAllDTO();
		for (JokesDTO joke : jokesService.findAllDTO()) {
			System.out.println(joke.getId());
		}
		return ResponseEntity.ok(jokesService.findAllDTO().stream().sorted((j1, j2) -> j1.getId().compareTo(j2.getId())));
	}
	

	
}
