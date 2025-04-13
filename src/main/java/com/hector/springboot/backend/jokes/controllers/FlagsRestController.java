package com.hector.springboot.backend.jokes.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hector.springboot.backend.jokes.models.entity.Flags;
import com.hector.springboot.backend.jokes.models.services.IFlagsService;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api")
public class FlagsRestController {
	@Autowired
	private IFlagsService flagsService;
	@GetMapping("/flags")
	public List<Flags> index(){
		return flagsService.findAll();
	}
}
