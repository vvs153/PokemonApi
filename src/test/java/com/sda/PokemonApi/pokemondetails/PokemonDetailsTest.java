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
        assertEquals(855, result.getWeight());
    }
}