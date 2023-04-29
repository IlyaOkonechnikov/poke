package com.alea.pokeapi.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/rpc/v1/pokemons")
public class PokemonController {

  @GetMapping("/pokemons/heaviest")
  public List<Pokemon> getHeaviestPokemons(@RequestParam int page, @RequestParam int size) {
    List<Pokemon> pokemons = pokemonService.getAllPokemons();
    Collections.sort(pokemons, Comparator.comparing(Pokemon::getWeight).reversed());
    return getPokemonsByPage(pokemons, page, size);
  }


}
