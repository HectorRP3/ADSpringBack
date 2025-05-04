package com.hector.springboot.backend.jokes.mapper;

import com.hector.springboot.backend.jokes.models.dto.CategoriesDTO;
import com.hector.springboot.backend.jokes.models.entity.Categories;

public class CategoriesMapper {
	// Convert entity to DTO
	public static CategoriesDTO toDTO(Categories categories) {
		CategoriesDTO categoriesDTO = new CategoriesDTO();
		categoriesDTO.setId(categories.getId());
		categoriesDTO.setCategory(categories.getCategory());
		return categoriesDTO;
	}
}
