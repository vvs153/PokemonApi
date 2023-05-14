package com.sda.PokemonApi.pokemondetails;

import com.sda.PokemonApi.NoPokemonFoundException;
import com.sda.PokemonApi.PokemonApiItemRepository;
import com.sda.PokemonApi.PokemonItemEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PokemonDetailsService{
    private final PokemonApiItemRepository pokemonApiItemRepository;
    private final String url;

    public PokemonDetailsService(PokemonApiItemRepository pokemonApiItemRepository,  @Value("${pokeapi.details.url}") String url) {
        this.pokemonApiItemRepository = pokemonApiItemRepository;
        this.url = url;
    }
    String getPokemonDetailsUrl(String pokemonName) {
        PokemonItemEntity pokemonItemEntity =
                pokemonApiItemRepository.findByName(pokemonName)
                        .orElseThrow(()->new NoPokemonFoundException(pokemonName));
        return String.format(url, pokemonItemEntity.getId());
    }

}
