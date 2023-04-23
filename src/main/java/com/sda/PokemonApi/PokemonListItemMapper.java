package com.sda.PokemonApi;

import org.springframework.stereotype.Component;

@Component
public class PokemonListItemMapper {

    PokemonItemEntity toEntity(PokemonListItem pokemonListItem){
        String url = pokemonListItem.getUrl();
        Long id = Long.parseLong(pokemonListItem.getUrl().substring(34,url.length()-1));
        return new PokemonItemEntity(
                id,
                pokemonListItem.getName()
        );
    }

}
