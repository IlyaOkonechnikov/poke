package com.alea.pokeapi.service;

import com.alea.pokeapi.config.ServiceProperties;
import com.alea.pokeapi.data.PokemonDto;
import com.alea.pokeapi.data.PokemonListDto;
import com.alea.pokeapi.service.client.PokeApiClient;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PokeApiService {

  private static final Comparator<PokemonDto> HEIGHT_COMPARATOR = Comparator.comparingInt(PokemonDto::getHeight).reversed();

  private final PokeApiClient client;
  private final ServiceProperties properties;

  public List<PokemonDto> getTopPokemons() {
    return getAllPokemonIds()
        .stream()
        .map(client::getPokemonById)
        .sorted(HEIGHT_COMPARATOR)
        .limit(5)
        .collect(Collectors.toList());
  }

  public List<Integer> getAllPokemonIds() {
    final PokemonListDto listToIdentifyCount = client.getAll(0, 0);
    return client.getAll(listToIdentifyCount.getCount(), 0).getResults()
        .stream()
        .map(result -> Integer.valueOf(result.getUrl().split("/")[6]))
        .collect(Collectors.toList());
  }
}
