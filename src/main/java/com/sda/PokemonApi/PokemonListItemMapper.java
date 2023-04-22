package com.sda.PokemonApi;

import org.springframework.stereotype.Component;

@Component
public class PokemonListItemMapper {

    PokemonItemEntity toEntity(PokemonListItem pokemonListItem){
        return new PokemonItemEntity(
                pokemonListItem.getName(),
                pokemonListItem.getUrl()
        );
    }

}
