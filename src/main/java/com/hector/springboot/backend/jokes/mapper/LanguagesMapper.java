package com.hector.springboot.backend.jokes.mapper;

import org.springframework.stereotype.Component;

import com.hector.springboot.backend.jokes.models.dto.LanguagesDTO;
import com.hector.springboot.backend.jokes.models.entity.Language;
@Component

public class LanguagesMapper {
	
	// Convert entity to DTO
	public static LanguagesDTO toDTO(Language language) {
		LanguagesDTO languagesDTO = new LanguagesDTO();
		languagesDTO.setId(language.getId());
		languagesDTO.setCode(language.getCode());
		languagesDTO.setLanguage(language.getLanguage());
		return languagesDTO;
	}
}
