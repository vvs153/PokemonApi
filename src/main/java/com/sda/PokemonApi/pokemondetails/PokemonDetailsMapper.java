package com.sda.PokemonApi.pokemondetails;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PokemonDetailsMapper {
    PokemonDetailsEntity toEntity(PokemonDetails pokemonDetails){
        String image = null;
        if(pokemonDetails.getSprites()!=null
                && pokemonDetails.getSprites().getOther()!=null
                && pokemonDetails.getSprites().getOther().getOfficialArtwork()!=null){
        image = pokemonDetails.getSprites().getOther().getOfficialArtwork().getFrontDefault();}
        List<String> abilities = null;
        if(pokemonDetails.getAbilities()!=null){
        abilities = pokemonDetails
                .getAbilities()
                .stream()
                .map(item -> item.getAbility().getName())
                .toList();}
        List<String> types = null;
        if(pokemonDetails.getTypes()!=null){
         types = pokemonDetails
                .getTypes()
                .stream()
                .map(item -> item.getType().getName())
                .toList();}
        return new PokemonDetailsEntity(
                pokemonDetails.getName(),
                pokemonDetails.getHeight(),
                pokemonDetails.getWeight(),
                image,
                types,
                abilities
        );
    }
}