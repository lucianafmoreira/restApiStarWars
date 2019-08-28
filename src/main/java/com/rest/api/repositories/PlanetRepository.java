package com.rest.api.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.rest.api.documents.Planet;

public interface PlanetRepository extends MongoRepository<Planet, String> {

	public List<Planet> findByNameLikeIgnoreCase(String name);
	
}
