package com.alea.pokeapi.dto;

import java.util.List;

import lombok.Data;

@Data
public class PokemonPage {
  private String next;
  private List<Result> results;

  private class Result {
    private String name;
    private String url;
  }
}
