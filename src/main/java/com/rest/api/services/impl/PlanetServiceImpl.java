package com.rest.api.services.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.rest.api.documents.Planet;
import com.rest.api.repositories.PlanetRepository;
import com.rest.api.services.PlanetService;

@Service
public class PlanetServiceImpl implements PlanetService {

	@Autowired
	private PlanetRepository planetRespository;

	private static final String SWAPI_URL = "https://swapi.co/api/planets/?search=";

	@Override
	public List<Planet> listAll() {
		return this.planetRespository.findAll();
	}

	@Override
	public Planet listById(String id) {
		return this.planetRespository.findOne(id);
	}

	@Override
	public List<Planet> listByName(String name) {
		return planetRespository.findByNameLikeIgnoreCase(name);
	}

	@Override
	public Planet register(Planet planet) {

		Integer qtd = PlanetServiceImpl.getNumberOfAppearances(planet.getName());

		if (qtd == null) {
			return null;
		}

		planet.setMovieAppearances(qtd);
		return this.planetRespository.save(planet);
	}

	@Override
	public void remove(String id) {
		this.planetRespository.delete(id);
	}

	private static Integer getNumberOfAppearances(String name) {
		StringBuilder completeUrl = new StringBuilder();
		completeUrl.append(SWAPI_URL).append(name);

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();

		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

		Object object;
		try {
			object = restTemplate.exchange(completeUrl.toString(), HttpMethod.GET,
					new HttpEntity<String>("parameters", headers), Object.class);
		} catch (Exception e) {
			return null;
		}

		Gson gson = new Gson();
		JsonArray results = gson.fromJson(gson.toJson(object), JsonObject.class).getAsJsonObject("body")
				.getAsJsonArray("results");

		JsonElement correctResult = null;

		for (JsonElement e : results) {
			if (e.getAsJsonObject().get("name").getAsString().equalsIgnoreCase(name)) {
				correctResult = e;
			}
		}

		if (correctResult == null) {
			return 0;
		} else {
			return correctResult.getAsJsonObject().getAsJsonArray("films").size();
		}
	}

}
