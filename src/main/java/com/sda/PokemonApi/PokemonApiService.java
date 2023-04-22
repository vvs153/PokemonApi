package com.sda.PokemonApi;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PokemonApiService {

    private final PokemonApiNetworkRepository pokemonApiNetworkRepository;


    public PokemonApiService(PokemonApiNetworkRepository pokemonApiNetworkRepository) {
        this.pokemonApiNetworkRepository = pokemonApiNetworkRepository;
    }

   public List<PokemonListItem> getPokemonListResult(){
        List<PokemonListItem> results = new ArrayList<>();
        PokemonList result;
        int limit = 100;
        int offset = 0;
        do{
            result = pokemonApiNetworkRepository.fetchPokemonListResult(limit,offset);
            results.addAll(result.getResults());
            offset +=limit;
        } while (result.getNext() != null);
        return results;
    }
}
