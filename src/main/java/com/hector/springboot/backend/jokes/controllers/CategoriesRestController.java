package com.hector.springboot.backend.jokes.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hector.springboot.backend.jokes.mapper.CategoriesMapper;
import com.hector.springboot.backend.jokes.models.dto.CategoriesDTO;
import com.hector.springboot.backend.jokes.models.entity.Categories;
import com.hector.springboot.backend.jokes.repository.ICategory;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class CategoriesRestController {
	
	@Autowired
	private ICategory categoriesService;
	
	@GetMapping("/category")
	public List<CategoriesDTO> getCategories() {
		return categoriesService.findAll().stream().map(CategoriesMapper::toDTO).collect(Collectors.toList());
	}
	
	@GetMapping("/category/{id}")
	public CategoriesDTO getCategoryById(@PathVariable Integer id) {
		return categoriesService.findById(id).map(CategoriesMapper::toDTO).orElse(null);
	}
	
	@PostMapping("/category")
	public CategoriesDTO createCategory(@RequestBody Categories categoriesDTO) {
		Categories categories = new Categories();
		categories.setCategory(categoriesDTO.getCategory());
		categoriesService.save(categories);
		return CategoriesMapper.toDTO(categories);
	}
	
	@PutMapping("/category/{id}")
	public CategoriesDTO updateCategory(@PathVariable Integer id, @RequestBody CategoriesDTO categoriesDTO) {
        Categories categories = categoriesService.findById(id).orElse(null);
        if (categories != null) {
            categories.setCategory(categoriesDTO.getCategory());
            categoriesService.save(categories);
        }
        return CategoriesMapper.toDTO(categories);
        
	}
	
			
}
