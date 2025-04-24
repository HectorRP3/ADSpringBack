package com.hector.springboot.backend.jokes.models.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hector.springboot.backend.jokes.models.dto.FlagsDTO;
import com.hector.springboot.backend.jokes.models.entity.Flags;
@Service
public interface IFlagsService {
	public List<Flags> findAll();
	public List<FlagsDTO> findAllDTO();
	public FlagsDTO findByIdDTO(Long id);
	
	public Flags findById(Long id);
	public Flags save(Flags flags);
	public void delete(Long id);
	}
