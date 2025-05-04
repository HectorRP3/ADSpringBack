package com.hector.springboot.backend.jokes.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hector.springboot.backend.jokes.mapper.LanguagesMapper;
import com.hector.springboot.backend.jokes.models.dto.LanguagesDTO;
import com.hector.springboot.backend.jokes.models.entity.Language;
import com.hector.springboot.backend.jokes.models.services.IJokesService;
import com.hector.springboot.backend.jokes.repository.ILanguage;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class LanguageRestController {
	@Autowired
	private IJokesService jokesService;
	@Autowired
	private ILanguage languageService;
	
	@GetMapping("/language")
	public List<LanguagesDTO> getLanguages() {
		return languageService.findAll().stream().map(LanguagesMapper::toDTO).collect(Collectors.toList());
	}
	
	@GetMapping("/language/{id}")
	public LanguagesDTO getLanguageById(@PathVariable Integer id) {
		return languageService.findById(id).map(LanguagesMapper::toDTO).orElse(null);
	}
	
	@PostMapping("/language")
	public LanguagesDTO createLanguage(@RequestBody Language languageDTO) {
		Language language = new Language();
		language.setCode(languageDTO.getCode());
		language.setLanguage(languageDTO.getLanguage());
		languageService.save(language);
		return LanguagesMapper.toDTO(language);
	}
	
	@PutMapping("/language/{id}")
	public LanguagesDTO updateLanguage(@PathVariable Integer id, @RequestBody Language languageDTO) {
		Language language = languageService.findById(id).orElse(null);
		if (language != null) {
			language.setCode(languageDTO.getCode());
			language.setLanguage(languageDTO.getLanguage());
			languageService.save(language);
		}
		return LanguagesMapper.toDTO(language);
	}
	

}
