package com.hector.springboot.backend.jokes.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hector.springboot.backend.jokes.models.dao.IprimeraVezDAO;
import com.hector.springboot.backend.jokes.models.entity.PrimeraVez;
@Service
public class PrimeraVezServiceImpl implements IPrimeraVezService {

	@Autowired 
	private IprimeraVezDAO primeraVezDAO;
	
	@Override
	@Transactional(readOnly = true)
	public List<PrimeraVez> findAll() {
		return (List<PrimeraVez>) primeraVezDAO.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public PrimeraVez findById(Long id) {
		PrimeraVez primeraVez = primeraVezDAO.findById(id).orElse(null);
		return primeraVez;
	}

	@Override
	@Transactional
	public PrimeraVez save(PrimeraVez primeraVez) {
		PrimeraVez primeraVezSaved = primeraVezDAO.save(primeraVez);
		return primeraVezSaved;
	}

	@Override
	@Transactional
	public void delete(Long id) {
		primeraVezDAO.deleteById(id);
	}

}
