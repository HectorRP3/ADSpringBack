package com.hector.springboot.backend.jokes.models.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hector.springboot.backend.jokes.mapper.JokesMapper;
import com.hector.springboot.backend.jokes.models.dao.IjokesDAO;
import com.hector.springboot.backend.jokes.models.dto.JokesDTO;
import com.hector.springboot.backend.jokes.models.entity.Jokes;

@Service
public class JokesServiceImpl implements IJokesService {

	@Autowired
	private IjokesDAO jokesDao;

	@Autowired
	private JokesMapper jokesMapper;

	@Override
	@Transactional(readOnly = true)
	public List<Jokes> findAll() {
		return (List<Jokes>) jokesDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<JokesDTO> findAllDTO() {
		return jokesDao.findAll().stream().map(jokesMapper::toDTO).collect(Collectors.toList());
	}
	
	@Transactional
	public JokesDTO findByIdDTO(Long id) {
		JokesDTO jokesDTO = null;
		Jokes jokes = jokesDao.findById(id).orElse(null);
		if (jokes != null) {
			jokesDTO = jokesMapper.toDTO(jokes);
		} 
		return jokesDTO;
	}

	@Override
	public Jokes findById(Long id) {
		return jokesDao.findById(id).orElse(null);
	}

	
	
	
}
