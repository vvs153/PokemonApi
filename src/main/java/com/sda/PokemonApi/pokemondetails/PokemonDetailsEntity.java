package com.sda.PokemonApi.pokemondetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PokemonDetailsEntity {

    private Integer height;
    private Integer weight;

    private String image;
    private List<String> types;
    private List<String> abilities;


}
