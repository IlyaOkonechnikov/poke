package com.alea.pokeapi.config;

import com.alea.pokeapi.service.PokeApiService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

@Configuration
public class MockedServiceConfig {

    @Bean
    public PokeApiService service() {
        return mock(PokeApiService.class);
    }
}
