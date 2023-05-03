package com.alea.pokeapi.data;

import java.util.Comparator;
import java.util.function.Supplier;

public enum PokemonTopType {

    HEAVIEST(() -> Comparator.comparingInt(PokemonDto::getWeight).reversed()),
    HIGHEST(() -> Comparator.comparingInt(PokemonDto::getHeight).reversed()),
    EXPERIENCED(() -> Comparator.comparingLong(PokemonDto::getBaseExperience).reversed());

    private final Supplier<Comparator<PokemonDto>> comparatorSupplier;

    PokemonTopType(Supplier<Comparator<PokemonDto>> comparatorSupplier) {
        this.comparatorSupplier = comparatorSupplier;
    }

    public Comparator<PokemonDto> getComparator() {
        return comparatorSupplier.get();
    }
}