package com.hector.springboot.backend.jokes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.hector.springboot.backend.jokes.models.entity.Categories;

@RepositoryRestResource(path = "categories", collectionResourceRel = "categories")
public interface ICategory extends JpaRepository<Categories, Integer> {

}
