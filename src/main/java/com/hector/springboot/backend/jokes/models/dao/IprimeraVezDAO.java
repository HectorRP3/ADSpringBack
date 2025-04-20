package com.hector.springboot.backend.jokes.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.hector.springboot.backend.jokes.models.entity.Jokes;
import com.hector.springboot.backend.jokes.models.entity.PrimeraVez;

public interface IprimeraVezDAO extends CrudRepository<PrimeraVez, Long>{

}
