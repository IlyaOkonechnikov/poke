package com.alea.pokeapi.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

public final class TestObjectsUtil {

    private static final ObjectMapper mapper = new ObjectMapper();

    private TestObjectsUtil() {
    }

    public static <T> T readJsonFile(TypeReference<T> typeReference, String fileName) {
        InputStream inputStream;
        try {
            inputStream = new ClassPathResource(fileName).getInputStream();
            return mapper.readValue(inputStream, typeReference);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    public static PokemonListDto getAllPokemonsResponse() {
//        InputStream inputStream;
//        try {
//            inputStream = new ClassPathResource("all_pokemons.json").getInputStream();
//            return mapper.readValue(inputStream, PokemonListDto.class);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public static List<PokemonDto> getPokemonsByIdResponse() {
//        InputStream inputStream;
//        try {
//            inputStream = new ClassPathResource("pokemons_by_id.json").getInputStream();
//            return mapper.readValue(inputStream, List.class);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
