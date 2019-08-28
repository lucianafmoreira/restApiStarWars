package com.rest.api.repositories;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rest.api.documents.Planet;
import com.rest.api.repositories.PlanetRepository;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataMongoTest
public class PlanetRepositoryTests {

	@Autowired
	PlanetRepository planetRepository;

	private String id1 = "1";
	private String name1 = "Alderaan";
	private String climate1 = "temperate";
	private String terrain1 = "grasslands, mountains";
	private int qtd1 = 2;
	private Planet planet1 = new Planet();

	private String id2 = "2";
	private String name2 = "Tatooine";
	private String climate2 = "arid";
	private String terrain2 = "desert";
	private int qtd2 = 5;
	private Planet planet2 = new Planet();

	@Test
	public void econtrarPorNome() {
		planetRepository.save(planet1);
		planetRepository.save(planet2);

		List<Planet> listPlanet = planetRepository.findByNameLikeIgnoreCase(name1);
		assertThat(listPlanet).hasSize(1).extracting("name").contains(name1);
	}

	@Test
	public void encontrarPorNomeMultiplosResultados() {
		planetRepository.save(planet1);
		planetRepository.save(planet2);

		List<Planet> listPlanet = planetRepository.findByNameLikeIgnoreCase("name");
		assertEquals(listPlanet.size(), 2);
	}
}
