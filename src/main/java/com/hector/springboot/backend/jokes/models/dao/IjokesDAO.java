package com.hector.springboot.backend.jokes.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.hector.springboot.backend.jokes.models.dto.JokesDTO;
import com.hector.springboot.backend.jokes.models.entity.Jokes;

public interface IjokesDAO  extends JpaRepository<Jokes, Long>{
	
	

}
