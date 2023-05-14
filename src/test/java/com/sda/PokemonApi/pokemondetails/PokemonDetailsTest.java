package com.sda.PokemonApi.pokemondetails;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.*;

class PokemonDetailsTest {

    @Test
    void given_sample_pokemon_details_response_when_deserializing_then_object_should_be() throws IOException {
        //given
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //when
        PokemonDetails result = objectMapper.readValue(
                new File("src/test/java/resources/pokemon_details_sample_response.json")
                , PokemonDetails.class
        );


        assertEquals(2, result.getAbilities().size());
        assertFalse(result.getAbilities().get(0).isHidden());
        assertEquals(1, result.getAbilities().get(0).getSlot());
        assertEquals("torrent", result.getAbilities().get(0).getAbility().getName());
        assertEquals("https://pokeapi.co/api/v2/ability/67/", result.getAbilities().get(0).getAbility().getUrl());
        assertEquals(855, result.getWeight());
        assertEquals(1, result.getTypes().get(0).getSlot());
        assertEquals("water", result.getTypes().get(0).getType().getName());
        assertEquals("https://pokeapi.co/api/v2/type/11/", result.getTypes().get(0).getType().getUrl());
        assertEquals(16, result.getHeight());
        assertEquals("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/9.png",
                result.getSprites().getOther().getOfficialArtwork().getFrontDefault());
        assertEquals("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/shiny/9.png",
                result.getSprites().getOther().getOfficialArtwork().getFrontShiny());
    }
}