package com.hector.springboot.backend.jokes.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hector.springboot.backend.jokes.models.dao.ItelefonosDAO;
import com.hector.springboot.backend.jokes.models.entity.Telefonos;

@Service
public class TelefonosServiceImpl implements ITelefonosService {

	@Autowired
	private ItelefonosDAO telefonosDAO;
	
	@Override
	public List<Telefonos> findAll() {
		return (List<Telefonos>) telefonosDAO.findAll();
	}

	@Override
	public Telefonos findById(Long id) {
		Telefonos telefono = telefonosDAO.findById(id).orElse(null);
		return telefono;
	}

	@Override
	public Telefonos save(Telefonos telefono) {
		Telefonos telefonoSaved = telefonosDAO.save(telefono);
		return telefonoSaved;
	}

	@Override
	public void delete(Long id) {
        telefonosDAO.deleteById(id);
	}

}
