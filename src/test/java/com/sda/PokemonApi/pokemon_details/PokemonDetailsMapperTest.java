package com.sda.PokemonApi.pokemon_details;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PokemonDetailsMapperTest {


  @Test
    void given_empty_pokemon_details_from_network_repository_when_map_to_entity_then_pokemon_details_entity_should_be_returned(){
      PokemonDetailsMapper pokemonDetailsMapper = new PokemonDetailsMapper();
      PokemonDetails pokemonDetails = new PokemonDetails(
              null,
            null,
              200,
              20,
              null,
              null
      );

    PokemonDetailsEntity result = pokemonDetailsMapper.toEntity(pokemonDetails);

    assertEquals(200, result.getHeight());
    assertEquals(20, result.getWeight());
    assertNull(result.getName());
    assertNull(result.getImage());
    assertNull(result.getAbilities());
    assertNull(result.getTypes());
  }

  @Test
  void given_pokemon_details_from_network_repository_when_map_to_entity_then_pokemon_details_entity_should_be_returned() {
    //given
    PokemonDetailsMapper pokemonDetailsMapper = new PokemonDetailsMapper();
    PokemonSpritesResult sprites = new PokemonSpritesResult(
            new PokemonOther(
                    new PokemonOfficialArtwork("image1", "image2")
            )
    );
    List<PokemonAbilityWrapperResult> abilities = Arrays.asList(
            new PokemonAbilityWrapperResult(
                    new PokemonAbilityResult("test", "testurl"),
                    false,
                    1
            ),
            new PokemonAbilityWrapperResult(
                    new PokemonAbilityResult("test1", "test1url"),
                    true,
                    2
            )
    );
    List<PokemonTypeWrapperResult> types = Arrays.asList(
            new PokemonTypeWrapperResult(
                    1,
                    new PokemonType("testtype", "testtypeurl")
            ),
            new PokemonTypeWrapperResult(
                    2,
                    new PokemonType("testtype1", "testtypeurl1")
            )
    );
    PokemonDetails pokemonDetails = new PokemonDetails(
            "kek",
            abilities,
            200,
            20,
            types,
            sprites
    );

    PokemonDetailsEntity result = pokemonDetailsMapper.toEntity(pokemonDetails);

    assertEquals("kek",result.getName());
    assertEquals(200, result.getHeight());
    assertEquals(20, result.getWeight());
    assertEquals("image1", result.getImage());
    assertEquals(Arrays.asList("test", "test1"), result.getAbilities());
    assertEquals(Arrays.asList("testtype", "testtype1"), result.getTypes());
  }

}