package com.hector.springboot.backend.jokes.models.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hector.springboot.backend.jokes.mapper.FlagsMapper;
import com.hector.springboot.backend.jokes.models.dao.IflagsDAO;
import com.hector.springboot.backend.jokes.models.dto.FlagsDTO;
import com.hector.springboot.backend.jokes.models.entity.Flags;

@Service
public class FlagsServiceImpl implements IFlagsService {

	@Autowired
	private IflagsDAO flagsDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Flags> findAll() {
		return (List<Flags>) flagsDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<FlagsDTO> findAllDTO() {
		return flagsDao.findAll().stream().map(FlagsMapper::toFlagsDTO).collect(Collectors.toList());
	}

	@Transactional
	public FlagsDTO findByIdDTO(Long id) {
		FlagsDTO flagsDTO = null;
		Flags flags = flagsDao.findById(id).orElse(null);
		if (flags != null) {
			flagsDTO = FlagsMapper.toFlagsDTO(flags);
		}
		return flagsDTO;
	}

	@Override
	@Transactional(readOnly = true)
	public Flags findById(Long id) {
		return flagsDao.findById(id).orElse(null);
	}

	@Override
	public Flags save(Flags flags) {
		return flagsDao.save(flags);
	}

	@Override
	public void delete(Long id) {
		flagsDao.deleteById(id);
	}
}
