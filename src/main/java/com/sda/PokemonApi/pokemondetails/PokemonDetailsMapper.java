package com.sda.PokemonApi.pokemondetails;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PokemonDetailsMapper {
    PokemonDetailsEntity toEntity(PokemonDetails pokemonDetails){
        String image = pokemonDetails.getSprites().getOther().getOfficialArtwork().getFrontDefault();
        List<String> abilities = pokemonDetails
                .getAbilities()
                .stream()
                .map(item -> item.getAbility().getName())
                .toList();
        List<String> types = pokemonDetails
                .getTypes()
                .stream()
                .map(item -> item.getType().getName())
                .toList();
        return new PokemonDetailsEntity(
                pokemonDetails.getHeight(),
                pokemonDetails.getWeight(),
                image,
                types,
                abilities
        );
    }
}