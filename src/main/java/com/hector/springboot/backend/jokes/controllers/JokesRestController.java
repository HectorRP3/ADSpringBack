package com.hector.springboot.backend.jokes.controllers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hector.springboot.backend.jokes.mapper.JokesMapper;
import com.hector.springboot.backend.jokes.models.dto.JokesDTO;
import com.hector.springboot.backend.jokes.models.entity.Categories;
import com.hector.springboot.backend.jokes.models.entity.Flags;
import com.hector.springboot.backend.jokes.models.entity.Jokes;
import com.hector.springboot.backend.jokes.models.entity.Language;
import com.hector.springboot.backend.jokes.models.entity.Types;
import com.hector.springboot.backend.jokes.models.services.IFlagsService;
import com.hector.springboot.backend.jokes.models.services.IJokesService;
import com.hector.springboot.backend.jokes.models.services.IPrimeraVezService;
import com.hector.springboot.backend.jokes.repository.ICategory;
import com.hector.springboot.backend.jokes.repository.ILanguage;
import com.hector.springboot.backend.jokes.repository.IType;

import jakarta.*;
import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class JokesRestController {
	@Autowired
	private IJokesService jokesService;
	@Autowired
	private IPrimeraVezService primeraVezService;
	@Autowired
	private IFlagsService flagsService;
	@Autowired
	private ICategory categoryService;
	@Autowired
	private ILanguage languageService;
	@Autowired
	private IType typeService;
	
	
	@Autowired
	private JokesMapper jokesMapper;

	@GetMapping("/jokes")
	public ResponseEntity<?> search(@RequestParam(name = "texto", required = false)String texto) {
		Map<String, Object> response = new HashMap<>();
		List<JokesDTO> jokesDTOList = null;
		try {
			if(texto!=null) {
				/*jokesDTOList = jokesService.findAllDTO().stream()
						.filter(j -> {
							if (j.getText1() != null) {
								return j.getText1().toLowerCase().contains(texto.toLowerCase());
							}
							return false;
						})
						.collect(Collectors.toList());*/
				jokesDTOList = jokesService.findByText(texto).stream().map(jokesMapper::toDTO).collect(Collectors.toList());
			}else {
				jokesDTOList = jokesService.findAllDTO().stream().sorted((j1, j2) -> j1.getId().compareTo(j2.getId()))
						.toList();
				return new ResponseEntity<List<JokesDTO>>(jokesDTOList, HttpStatus.OK);
			}
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (jokesDTOList.isEmpty()) {
			response.put("mensaje", "No hay chistes que contengan el texto: ".concat(texto));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<JokesDTO>>(jokesDTOList, HttpStatus.OK);

	}
	
	@GetMapping("/jokes/categories")
	public ResponseEntity<?> getCategories() {
		Map<String, Object> response = new HashMap<>();
		List<Categories> categories = null;
		try {
			categories = categoryService.findAll();
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (categories.isEmpty()) {
			response.put("mensaje", "No hay categorias disponibles.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Categories>>(categories, HttpStatus.OK);

	}
	@GetMapping("/jokes/languages")
	public ResponseEntity<?> getLanguages() {
		Map<String, Object> response = new HashMap<>();
		List<Language> languages = null;
		try {
			languages = languageService.findAll();
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (languages.isEmpty()) {
			response.put("mensaje", "No hay idiomas disponibles.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Language>>(languages, HttpStatus.OK);

	}
	
	@GetMapping("/jokes/types")
	public ResponseEntity<?> getTypes() {
		Map<String, Object> response = new HashMap<>();
		List<Types> types = null;
		try {
			types = typeService.findAll();
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (types.isEmpty()) {
			response.put("mensaje", "No hay tipos disponibles.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Types>>(types, HttpStatus.OK);

	}
	
	
	@GetMapping("/jokes/{id}")
	public ResponseEntity<?> show(@PathVariable Long id){
		JokesDTO jokesDTO = null;
		Jokes jokes = null;
		Map<String,Object> response = new HashMap<>();
		try {
			jokes = jokesService.findById(id);
			jokesDTO = jokesMapper.toDTO(jokes);
	    } catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
		if (jokesDTO == null) {
			response.put("mensaje", "El chiste ID: ".concat(id.toString()).concat(" no existe en la base de datos!"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<JokesDTO>(jokesDTO, HttpStatus.OK);
	}
	
	@PostMapping("/jokes")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody Jokes jokes, BindingResult result) {
		Jokes jokesNew = null;
		Map<String,Object> response = new HashMap<>();
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err->"El campo '"+err.getField()+"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
		}
		try {
			jokesNew = jokesService.save(jokes);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Jokes>(jokesNew, HttpStatus.CREATED);
	}
	
	@PutMapping("/jokes/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Jokes jokes,BindingResult result, @PathVariable Long id){
		Jokes jokesActual = jokesService.findById(id);
		Jokes jokesUpdated = null;
		System.out.println(jokes);
		Map<String,Object> response = new HashMap<>();
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		if (jokesActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el chiste ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		System.out.println(jokes);

		try {
			System.out.println(jokes);
			jokesActual.setText1(jokes.getText1());
			jokesActual.setText2(jokes.getText2());
			jokesActual.setCategories(jokes.getCategories());
			jokesActual.setTypes(jokes.getTypes());
			jokesActual.setLanguage(jokes.getLanguage());
			Set<Flags> flags = new HashSet<>();
			jokes.getFlagses().forEach(f -> {
				Flags flag = flagsService.findById(f.getId());
				if (flag != null) {
					flags.add(flag);
				}
			});
			jokesActual.getFlagses().forEach(f -> {
				Flags flag = flagsService.findById(f.getId());
                if (flag != null) {
                    flags.add(flag);
                }
			});
			jokesActual.getFlagses().clear();
			jokesActual.setFlagses(flags);
            jokesUpdated = jokesService.save(jokesActual);
		} catch (DataAccessException e) {
			e.printStackTrace();
			response.put("mensaje", "Error al realizar la actualización en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		JokesDTO jokesDTO = jokesMapper.toDTO(jokesUpdated);
		response.put("mensaje", "El Jokes ha sido actualizado con éxito!");
		response.put("jokes", jokesDTO);
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED); 
	}
	
	@DeleteMapping("/jokes/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		
		try {
			Jokes jokes = jokesService.findById(id);
			if (jokes == null) {
				response.put("mensaje", "Error: no se pudo eliminar, el chiste ID: "
						.concat(id.toString().concat(" no existe en la base de datos!")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			/*jokes.getFlagses().clear();
			jokes.setCategories(null);
			jokes.setLanguage(null);
			jokes.setTypes(null);
			jokesService.save(jokes);*/
			jokesService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el chiste de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El chiste ha sido eliminado con éxito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/jokesWithprimeraVez")
	public ResponseEntity<?> getJokesWithPrimeraVez() {
		Map<String, Object> response = new HashMap<>();
		List<JokesDTO> jokesDTOList = null;
		try {
			jokesDTOList = jokesService.findAllDTO().stream().filter(j -> j.getPrimeraVez() != null)
					.collect(Collectors.toList());
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (jokesDTOList.isEmpty()) {
			response.put("mensaje", "No hay chistes asociados a una primera vez.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<JokesDTO>>(jokesDTOList, HttpStatus.OK);
		
	}
	
	@GetMapping("/jokesWithoutprimeraVez")
	public ResponseEntity<?> getJokesWithoutPrimeraVez() {
		Map<String, Object> response = new HashMap<>();
		List<JokesDTO> jokesDTOList = null;
		try {
			jokesDTOList = jokesService.findAllDTO().stream().filter(j -> j.getPrimeraVez() == null).sorted((j1,j2)->j1.getId().compareTo(j2.getId()))
					.collect(Collectors.toList());
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (jokesDTOList.isEmpty()) {
			response.put("mensaje", "No hay chistes sin asociar a una primera vez.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<JokesDTO>>(jokesDTOList, HttpStatus.OK);

	}
	
	
	

	
}

