package com.alea.pokeapi.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PokemonDto {
  private Long id;
  private String name;
  @JsonProperty("base_experience")
  private int baseExperience;
  private int height;
  private int weight;
}