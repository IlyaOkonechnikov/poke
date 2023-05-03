package com.alea.pokeapi.data;

import lombok.Data;

@Data
public class PokemonDto {
  private Long id;
  private String name;
  private int baseExperience;
  private int height;
  private int weight;
}