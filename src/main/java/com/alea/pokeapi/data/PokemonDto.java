package com.alea.pokeapi.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PokemonDto {
  private int id;
  private String name;
  @JsonProperty("base_experience")
  private int baseExperience;
  private int height;
  private int weight;
}