package com.alea.pokeapi.dto;

import java.util.List;

import lombok.Data;

@Data
public class PokemonListDto {
  private int count;
  private String next;
  private List<PokemonResultDto> results;
}
