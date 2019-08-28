package com.rest.api.services;

import java.util.List;

import com.rest.api.documents.Planet;

public interface PlanetService {
	
	List<Planet> listAll();
	
	List<Planet> listByName(String name);
	
	Planet listById(String id);
	
	Planet register(Planet planet);
	
	void remove(String id);

}
