package com.hector.springboot.backend.jokes.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hector.springboot.backend.jokes.models.dao.IflagsDAO;
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

}
