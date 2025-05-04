package com.hector.springboot.backend.jokes.mapper;

import org.springframework.stereotype.Component;

import com.hector.springboot.backend.jokes.models.dto.TypesDTO;
import com.hector.springboot.backend.jokes.models.entity.Types;

@Component
public class TypesMapper {
	
	// Convert entity to DTO
	public static TypesDTO toDTO(Types types) {
		TypesDTO typesDTO = new TypesDTO();
		typesDTO.setId(types.getId());
		typesDTO.setType(types.getType());
		return typesDTO;
	}

}
