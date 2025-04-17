package com.hector.springboot.backend.jokes.mapper;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.hector.springboot.backend.jokes.models.dto.JokesDTO;
import com.hector.springboot.backend.jokes.models.entity.Jokes;
@Component
public class JokesMapper {
	
	public JokesDTO toDTO(Jokes jokes) {
		JokesDTO dto = new JokesDTO();
		dto.setId(jokes.getId());
		dto.setText1(jokes.getText1());
		dto.setText2(jokes.getText2());
		if (jokes.getCategories() != null) {
			dto.setCategory(jokes.getCategories().getCategory());
		}
		if (jokes.getTypes() != null) {
			dto.setType(jokes.getTypes().getType());
		}
		if (jokes.getLanguage() != null) {
			dto.setLanguage(jokes.getLanguage().getLanguage());
		}
		dto.setFlags(jokes.getFlagses().stream().map(f->f.getFlag()).collect(Collectors.toSet()));
		return dto;
	}
	

}
