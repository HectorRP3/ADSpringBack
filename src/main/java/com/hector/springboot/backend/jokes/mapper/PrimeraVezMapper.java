package com.hector.springboot.backend.jokes.mapper;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.hector.springboot.backend.jokes.models.dto.PrimeraVezDto;
import com.hector.springboot.backend.jokes.models.entity.PrimeraVez;
import com.hector.springboot.backend.jokes.models.entity.Telefonos;

@Component
public class PrimeraVezMapper {
	
	public PrimeraVezDto toDTO(PrimeraVez primeraVez) {
		PrimeraVezDto dto = new PrimeraVezDto();
		dto.setId(primeraVez.getId());
		dto.setPrograma(primeraVez.getPrograma());
		if (primeraVez.getJokes() != null) {
			dto.setJokes(primeraVez.getJokes().getId());
		}
			dto.setFechaEmision(primeraVez.getFechaEmision());
		dto.setTelefonoses(primeraVez.getTelefonoses().stream().map(t->t.getNumero()).collect(Collectors.toSet()));
		return dto;
	}
}
