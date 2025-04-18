package com.hector.springboot.backend.jokes.mapper;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.hector.springboot.backend.jokes.models.dto.FlagsDTO;
import com.hector.springboot.backend.jokes.models.entity.Flags;
@Component
public class FlagsMapper {
	
	public static FlagsDTO toFlagsDTO(Flags flags) {
		FlagsDTO flagsDTO = new FlagsDTO();
		flagsDTO.setId(flags.getId());
		flagsDTO.setFlag(flags.getFlag());
		flagsDTO.setJokeses(flags.getJokeses().stream().map(j -> j.getId()).collect(Collectors.toSet()));
		return flagsDTO;
	}

}
