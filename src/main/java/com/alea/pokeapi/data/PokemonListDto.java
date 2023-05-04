package com.alea.pokeapi.data;

import lombok.Data;

import java.util.List;

@Data
public class PokemonListDto {
  private int count;
  private List<PokemonResultDto> results;
}
