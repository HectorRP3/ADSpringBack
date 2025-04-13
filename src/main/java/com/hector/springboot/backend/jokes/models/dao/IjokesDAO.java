package com.hector.springboot.backend.jokes.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.hector.springboot.backend.jokes.models.entity.Jokes;

public interface IjokesDAO  extends CrudRepository<Jokes, Long>{
	
	List<Jokes> findAll();

}
