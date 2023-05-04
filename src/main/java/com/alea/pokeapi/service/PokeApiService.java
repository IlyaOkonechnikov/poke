package com.alea.pokeapi.service;

import com.alea.pokeapi.config.ServiceProperties;
import com.alea.pokeapi.data.PokemonDto;
import com.alea.pokeapi.data.PokemonTopType;
import com.alea.pokeapi.service.client.PokeApiClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Slf4j
@Service
@EnableCaching
@RequiredArgsConstructor
public class PokeApiService {

    private final PokeApiClient client;
    private final ServiceProperties properties;

    @Cacheable(value = "${spring.cache.name}", key = "#topType")
    public List<PokemonDto> getTopPokemons(PokemonTopType topType) {
        log.debug("Getting top pokemons for {}", topType);
        long startTime = System.currentTimeMillis();

        List<Integer> allPokemonIds = getAllPokemonIds();
        List<CompletableFuture<PokemonDto>> pokemonFutures = allPokemonIds.stream()
                .map(id -> CompletableFuture.supplyAsync(() -> client.getPokemonById(id)))
                .toList();

        List<PokemonDto> topPokemons = pokemonFutures.stream()
                .map(CompletableFuture::join)
                .sorted(topType.getComparator())
                .limit(properties.getTopLimit())
                .collect(Collectors.toList());

        long endTime = System.currentTimeMillis();
        log.debug(
                "Got top {} {} pokemons in {} ms",
                properties.getTopLimit(), topType.toString().toLowerCase(), endTime - startTime
        );
        return topPokemons;
    }

    public List<Integer> getAllPokemonIds() {
        log.debug("Getting all pokemon ids");
        final int pokemonsCount = client.getAll(0, 0).getCount();
        List<Integer> allPokemonIds = client.getAll(pokemonsCount, 0).getResults()
                .stream()
                .map(result -> Integer.valueOf(result.getUrl().split("/")[6]))
                .collect(Collectors.toList());
        log.debug("Got {} pokemon ids", allPokemonIds.size());
        return allPokemonIds;
    }

    @Scheduled(fixedRateString ="${spring.cache.ttl}")
    @CacheEvict(value = "${spring.cache.name}", allEntries = true)
    public void evictCache() {
        log.debug("Evicting the cache");
    }
}
