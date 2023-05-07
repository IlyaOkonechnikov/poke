package com.alea.pokeapi.web.controller;

import com.alea.pokeapi.config.MockedServiceConfig;
import com.alea.pokeapi.config.WebConfig;
import com.alea.pokeapi.data.PokemonDto;
import com.alea.pokeapi.data.PokemonTopType;
import com.alea.pokeapi.service.PokeApiService;
import com.alea.pokeapi.util.TestObjectsUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureWebMvc
@AutoConfigureMockMvc
@SpringBootTest(classes = {
        JacksonAutoConfiguration.class,
        MockedServiceConfig.class,
        WebConfig.class
})
class PokeApiControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private PokeApiService service;

    @Test
    public void getTopPokemons_shouldReturnTop_when_correctType() throws Exception {
        PokemonTopType type = PokemonTopType.EXPERIENCED;
        List<PokemonDto> expectedResponse = TestObjectsUtil.readJsonFile(
                new TypeReference<>() {}, "pokemons_by_id.json"
        );
        when(service.getTopPokemons(type)).thenReturn(expectedResponse);

        String responseBody = mvc.perform(get("/rpc/v1/pokemons/top").param("topType", type.name()))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        List<PokemonDto> actualResponse = mapper.readValue(responseBody, new TypeReference<>() {});
        assertThat(expectedResponse).isEqualTo(actualResponse);
    }

    @Test
    public void getTopPokemons_shouldThrowBadRequestError_when_incorrectType() throws Exception {
        mvc.perform(get("/rpc/v1/pokemons/top").param("topType", "INCORRECT"))
                .andExpect(status().isBadRequest());
    }
}
