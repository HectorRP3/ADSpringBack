package com.hector.springboot.backend.jokes.models.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hector.springboot.backend.jokes.models.dto.JokesDTO;
import com.hector.springboot.backend.jokes.models.entity.Jokes;



@Service
public interface IJokesService {
	
	public List<Jokes> findAll();
	
	public List<JokesDTO> findAllDTO();
	
	
}
