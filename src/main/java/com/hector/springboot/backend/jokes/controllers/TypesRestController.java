package com.hector.springboot.backend.jokes.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hector.springboot.backend.jokes.mapper.TypesMapper;
import com.hector.springboot.backend.jokes.models.dto.TypesDTO;
import com.hector.springboot.backend.jokes.models.entity.Types;
import com.hector.springboot.backend.jokes.repository.IType;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class TypesRestController {
	
	@Autowired
	private IType typesService;
	
	@GetMapping("/type")
	public List<TypesDTO> listAll() {
		List<Types> typesList = typesService.findAll();
		return typesList.stream().map(TypesMapper::toDTO).collect(Collectors.toList());
	}
	
	@GetMapping("/type/{id}")
	public ResponseEntity<TypesDTO> findById(@PathVariable Integer id) {
		Types typesDTO = typesService.findById(id).orElse(null);
		if (typesDTO != null) {
			TypesDTO types = TypesMapper.toDTO(typesDTO);
			return ResponseEntity.ok(types);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/type")
	public ResponseEntity<TypesDTO> create(@RequestBody TypesDTO typesDTO) {
		Types types = new Types();
		types.setType(typesDTO.getType());
		typesService.save(types);
		return ResponseEntity.ok(TypesMapper.toDTO(types));
	}
	
	@PutMapping("/type/{id}")
	public ResponseEntity<TypesDTO> update(@PathVariable Integer id, @RequestBody TypesDTO typesDTO) {
		Types types = typesService.findById(id).orElse(null);
		if (types != null) {
			types.setType(typesDTO.getType());
			typesService.save(types);
			return ResponseEntity.ok(TypesMapper.toDTO(types));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	
}
