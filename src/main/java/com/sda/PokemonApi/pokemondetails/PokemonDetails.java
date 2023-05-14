package com.sda.PokemonApi.pokemondetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PokemonDetails {
    private List<PokemonAbilityWrapperResult> abilities;
    private Integer height;
    private Integer weight;
    private List<PokemonTypeWrapperResult> types;
    private PokemonSpritesResult sprites;
    }


