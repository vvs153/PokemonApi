package com.sda.PokemonApi.pokemondetails;

import com.sda.PokemonApi.PokemonList;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

public class PokemonApiDetailsNetworkRepository {
    private final String url;
    private final RestTemplate restTemplate;

    public PokemonApiDetailsNetworkRepository(@Value("${pokeapi.details.url}")String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }
    PokemonDetails fetchPokemonDetailsResult(int limit) {
        String fullUrl = String.format(url, limit );
        PokemonDetails result = restTemplate.getForObject(fullUrl, PokemonDetails.class);
        return result;
    }
}
