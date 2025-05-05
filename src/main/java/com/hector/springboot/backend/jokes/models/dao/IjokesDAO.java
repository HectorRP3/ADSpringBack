package com.hector.springboot.backend.jokes.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.hector.springboot.backend.jokes.models.entity.Jokes;

public interface IjokesDAO  extends CrudRepository<Jokes, Long>{
	
	List<Jokes> findAll();
	@Modifying
    @Query("DELETE FROM Jokes j WHERE j.language.id = :languageId")
    void deleteByLanguageId(@Param("languageId") Integer languageId);
	
	@Query("SELECT j from Jokes j WHERE LOWER(j.text1) LIKE LOWER(CONCAT('%', :text, '%'))")
	List<Jokes> findByText(@Param("text") String text);

}
