package com.alea.pokeapi.service.client;

import com.alea.pokeapi.dto.PokemonDto;
import com.alea.pokeapi.dto.PokemonPage;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(
    value = "poke-api",
    url = "${feign.client.config.poke-api.url}"
)
public interface PokeApiClient {

  @GetMapping("/api/v2/pokemon")
  List<PokemonPage> getPokemonsPage(@RequestParam int limit, @RequestParam int offset);

  @GetMapping("/api/v2/pokemon/{id}")
  PokemonDto getPokemonById(@PathVariable("id") int id);
}