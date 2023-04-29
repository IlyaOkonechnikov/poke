package com.alea.pokeapi.service;

import com.alea.pokeapi.config.ServiceProperties;
import com.alea.pokeapi.dto.PokemonDto;
import com.alea.pokeapi.service.client.PokeApiClient;

import org.springframework.stereotype.Service;

import java.util.List;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PokeApiService {

  private final PokeApiClient client;
  private final ServiceProperties properties;

  private List<PokemonDto> getAll() {
    client.getPokemonsPage()
  }
}
