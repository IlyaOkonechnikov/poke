package com.alea.pokeapi.web.controller;

import com.alea.pokeapi.dto.PokemonDto;
import com.alea.pokeapi.service.PokeApiService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rpc/v1/pokemons")
public class PokemonController {

  private final PokeApiService service;

  @GetMapping("/pokemons/heaviest")
  public List<PokemonDto> getHeaviestPokemons() {
    return service.getHeaviestPokemons();
  }
}
