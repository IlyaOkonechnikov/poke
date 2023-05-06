package com.alea.pokeapi.service.client;

import com.alea.pokeapi.data.PokemonDto;
import com.alea.pokeapi.data.PokemonListDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

public class PokeApiClientTest {

    private final PodamFactory factory = new PodamFactoryImpl();
    private final PokeApiClient pokeApiClient = mock(PokeApiClient.class);

    @AfterEach
    void tearDown() {
        reset(pokeApiClient);
    }

    @Test
    public void getAll_should_returnProperResponse() {
        int limit = 2;
        int offset = 0;
        PokemonListDto expectedResponse = factory.manufacturePojoWithFullData(PokemonListDto.class);

        when(pokeApiClient.getAll(limit, offset)).thenReturn(expectedResponse);

        PokemonListDto actualResponse = pokeApiClient.getAll(limit, offset);
        assertThat(actualResponse).isEqualTo(expectedResponse);
    }

    @Test
    public void getPokemonById_should_returnProperResponse() {
        PokemonDto expectedResponse = factory.manufacturePojoWithFullData(PokemonDto.class);

        when(pokeApiClient.getPokemonById(expectedResponse.getId())).thenReturn(expectedResponse);

        PokemonDto actualResponse = pokeApiClient.getPokemonById(expectedResponse.getId());
        assertThat(actualResponse).isEqualTo(expectedResponse);
    }
}