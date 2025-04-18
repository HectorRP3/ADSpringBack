package com.hector.springboot.backend.jokes.models.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.hector.springboot.backend.jokes.models.entity.Types;

@RepositoryRestResource(path = "types", collectionResourceRel = "types")
public interface IType extends JpaRepository<Types, Integer> {

}
