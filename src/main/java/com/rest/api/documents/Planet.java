package com.rest.api.documents;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Planet {

	@Id
	private String id;
	
	/*@Indexed(unique = true)
	@NotBlank
	@NotNull*/
	private String name;
	
//	@NotBlank
//	@NotNull
	private String climate;
	
//	@NotBlank
//	@NotNull
	private String terrain;
	
	private Integer movieAppearances;

public Planet() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@NotNull(message = "O nome do Planeta não pode ser vazio")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotNull(message = "O clima do Planeta não pode ser vazio")
	public String getClimate() {
		return climate;
	}

	public void setClimate(String climate) {
		this.climate = climate;
	}

	@NotNull(message = "O terreno do Planeta não pode ser vazio")
	public String getTerrain() {
		return terrain;
	}

	public void setTerrain(String terrain) {
		this.terrain = terrain;
	}

	public Integer getMovieAppearances() {
		return movieAppearances;
	}

	public void setMovieAppearances(Integer movieAppearances) {
		this.movieAppearances = movieAppearances;
	}
}
