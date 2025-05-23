package com.hector.springboot.backend.jokes.models.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hector.springboot.backend.jokes.models.dto.JokesDTO;
import com.hector.springboot.backend.jokes.models.entity.Jokes;



@Service
public interface IJokesService {
	
	public List<Jokes> findAll();
	
	public List<JokesDTO> findAllDTO();
	
	public JokesDTO findByIdDTO(Long id);
	
	public Jokes findById(Long id);
	
	public Jokes save(Jokes jokes);
	
	public void delete(Long id);
	
    public List<Jokes> findByText(String text);
	
	
	
}
