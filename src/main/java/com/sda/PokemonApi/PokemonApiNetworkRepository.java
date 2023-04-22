package com.sda.PokemonApi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PokemonApiNetworkRepository {
    private final String url;
    private final RestTemplate restTemplate;

    public PokemonApiNetworkRepository(@Value("${pokeapi.url}") String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }


    PokemonList fetchPokemonListResult(int limit, int offeset) {
        String fullUrl = String.format(url, limit, offeset);
        PokemonList result = restTemplate.getForObject(fullUrl, PokemonList.class);
        return result;
    }

}
