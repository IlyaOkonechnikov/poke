package com.alea.pokeapi.service.client;

import com.alea.pokeapi.data.PokemonDto;
import com.alea.pokeapi.data.PokemonListDto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
    value = "poke-api",
    url = "${feign.client.config.poke-api.url}"
)
public interface PokeApiClient {

  @GetMapping("/api/v2/pokemon")
  PokemonListDto getAll(@RequestParam int limit, @RequestParam int offset);

  @GetMapping("/api/v2/pokemon/{id}")
  PokemonDto getPokemonById(@PathVariable("id") int id);
}