package com.alea.pokeapi.web.controller;

import com.alea.pokeapi.data.PokemonDto;
import com.alea.pokeapi.data.PokemonTopType;
import com.alea.pokeapi.service.PokeApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rpc/v1/pokemons")
public class PokeApiController {

  private final PokeApiService service;

  @GetMapping("/top")
  public List<PokemonDto> getTopPokemons(@RequestParam PokemonTopType topType) {
    return service.getTopPokemons(topType);
  }
}
