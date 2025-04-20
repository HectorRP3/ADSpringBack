package com.hector.springboot.backend.jokes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.hector.springboot.backend.jokes.models.entity.Language;

@RepositoryRestResource(path = "languages", collectionResourceRel = "languages")
public interface ILanguage extends JpaRepository<Language, Integer> {

}
