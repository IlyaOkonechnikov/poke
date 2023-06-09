package com.alea.pokeapi.service;

import com.alea.pokeapi.config.ServiceProperties;
import com.alea.pokeapi.data.PokemonDto;
import com.alea.pokeapi.data.PokemonListDto;
import com.alea.pokeapi.data.PokemonTopType;
import com.alea.pokeapi.service.client.PokeApiClient;
import com.alea.pokeapi.util.TestObjectsUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class PokeApiServiceTest {

    private final PokeApiClient pokeApiClient = mock(PokeApiClient.class);
    private final ServiceProperties properties = mock(ServiceProperties.class);
    private final PokeApiService pokeApiService = new PokeApiService(pokeApiClient, properties);

    @AfterEach
    void tearDown() {
        reset(pokeApiClient, properties);
    }

    @ParameterizedTest
    @MethodSource("getTopTypes")
    public void getTopPokemons_should_returnCorrectTopForEachType(PokemonTopType topType) {
        int count = 100;
        int topLimit = 5;

        when(properties.getTopLimit()).thenReturn(topLimit);
        when(pokeApiClient.getAll(0, 0)).thenReturn(new PokemonListDto(count, Collections.emptyList()));
        when(pokeApiClient.getAll(count, 0)).thenReturn(
                TestObjectsUtil.readJsonFile(new TypeReference<>() {}, "all_pokemons.json")
        );
        when(pokeApiClient.getPokemonById(anyInt())).thenAnswer(i -> PokemonDto.builder()
                .baseExperience((int) i.getArguments()[0])
                .height((int) i.getArguments()[0])
                .weight((int) i.getArguments()[0])
                .build()
        );

        List<PokemonDto> topPokemons = pokeApiService.getTopPokemons(topType);
        for (int i = 0; i < topLimit; i++) {
            assertThat(topPokemons.get(i)).extracting(
                    PokemonDto::getBaseExperience,
                    PokemonDto::getHeight,
                    PokemonDto::getWeight
            ).containsExactly(count, count, count--);
        }
    }


    private static Stream<Arguments> getTopTypes() {
        return Stream.of(
                Arguments.of(PokemonTopType.HEAVIEST),
                Arguments.of(PokemonTopType.HIGHEST),
                Arguments.of(PokemonTopType.EXPERIENCED)
        );
    }
}