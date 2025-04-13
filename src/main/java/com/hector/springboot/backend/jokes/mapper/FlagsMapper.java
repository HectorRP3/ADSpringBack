package com.hector.springboot.backend.jokes.mapper;

import java.util.stream.Collectors;

import com.hector.springboot.backend.jokes.models.dto.FlagsDTO;
import com.hector.springboot.backend.jokes.models.entity.Flags;

public class FlagsMapper {
	
	public static FlagsDTO toFlagsDTO(Flags flags) {
		FlagsDTO flagsDTO = new FlagsDTO();
		flagsDTO.setId(flags.getId());
		flagsDTO.setFlag(flags.getFlag());
		flagsDTO.setJokesId(flags.getJokeses().stream().map(j -> j.getId()).collect(Collectors.toSet()));
		return flagsDTO;
	}

}
