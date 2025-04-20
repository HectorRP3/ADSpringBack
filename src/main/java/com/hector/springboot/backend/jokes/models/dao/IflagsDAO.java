package com.hector.springboot.backend.jokes.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.hector.springboot.backend.jokes.models.entity.Flags;
import com.hector.springboot.backend.jokes.proyecciones.ConFlags;

public interface IflagsDAO extends CrudRepository<Flags, Long> {
	List<Flags> findAll();
	 
	@Query("SELECT f FROM Flags f JOIN FETCH f.jokeses j WHERE j.id = :id")
	List<Flags> findByJokesId(@Param("id") Long id);
}
