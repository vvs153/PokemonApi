package com.sda.PokemonApi.pokemondetails;

import com.sda.PokemonApi.PokemonList;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class PokemonApiDetailsNetworkRepository {
    private final String url;
    private final RestTemplate restTemplate;

    public PokemonApiDetailsNetworkRepository(@Value("${pokeapi.details.url}")String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }
    PokemonDetails fetchPokemonDetailsResult(Long pokeId) {
        String fullUrl = String.format(url, pokeId );
        PokemonDetails result = restTemplate.getForObject(fullUrl, PokemonDetails.class);
        return result;
    }
}
