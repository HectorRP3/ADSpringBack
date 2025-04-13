package com.hector.springboot.backend.jokes.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.hector.springboot.backend.jokes.models.entity.Flags;

public interface IflagsDAO extends CrudRepository<Flags, Long> {

}
