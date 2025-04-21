package com.hector.springboot.backend.jokes.mapper;

import org.springframework.stereotype.Component;

import com.hector.springboot.backend.jokes.models.dto.TelefonosDTO;
import com.hector.springboot.backend.jokes.models.entity.Telefonos;
@Component
public class TelefonoMapper {
	
	public TelefonosDTO toDto(Telefonos telefono) {
		TelefonosDTO dto = new TelefonosDTO();
		dto.setId(telefono.getId());
		dto.setNumero(telefono.getNumero());
		if (telefono.getPrimeraVez() != null) {
			dto.setPrimeraVezId(telefono.getPrimeraVez().getId());
		}
		return dto;
	}
}
