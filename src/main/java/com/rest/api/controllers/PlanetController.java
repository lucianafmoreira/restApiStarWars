package com.rest.api.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.api.documents.Planet;
import com.rest.api.event.PlanetEvent;
import com.rest.api.responses.Response;
import com.rest.api.services.PlanetService;

@RestController
@RequestMapping(path = "/planets")
public class PlanetController {

	@Autowired
	private PlanetService planetService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public ResponseEntity<Response<List<Planet>>> listAllPlanets() {
		return ResponseEntity.ok(new Response<List<Planet>>(this.planetService.listAll()));
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<Planet>> listPlanetById(@PathVariable(name = "id") String id) {
		return ResponseEntity.ok(new Response<Planet>(this.planetService.listById(id)));
	}

	@GetMapping(value = "name/{name}")
	public ResponseEntity<List<Planet>> listPlanetByName(@PathVariable(name = "name") String name) {
		List<Planet> listPlanet = this.planetService.listByName(name);

		if (listPlanet != null) {
			return new ResponseEntity<>(listPlanet, HttpStatus.OK);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<Planet> savePlanet(@Valid @RequestBody Planet Planet, HttpServletResponse response) {

		Planet savedPlanet = this.planetService.register(Planet);

		if (savedPlanet != null) {
			publisher.publishEvent(new PlanetEvent(this, response, Planet.getId()));
			return ResponseEntity.status(HttpStatus.CREATED).body(savedPlanet);
		} else {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
		}

	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Response<Integer>> removePlanet(@PathVariable(name = "id") String id) {
		this.planetService.remove(id);
		return ResponseEntity.ok(new Response<Integer>(1));
	}

}
