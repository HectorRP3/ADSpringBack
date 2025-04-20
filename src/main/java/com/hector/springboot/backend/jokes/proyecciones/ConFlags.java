package com.hector.springboot.backend.jokes.proyecciones;

import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import com.hector.springboot.backend.jokes.models.entity.Flags;

@Projection(name = "conFlags", types = { Flags.class })
public interface ConFlags {
	
	  Long getId();           
}
