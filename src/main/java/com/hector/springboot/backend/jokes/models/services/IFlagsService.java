package com.hector.springboot.backend.jokes.models.services;

import java.util.List;

import com.hector.springboot.backend.jokes.models.entity.Flags;

public interface IFlagsService {
	public List<Flags> findAll();
}
