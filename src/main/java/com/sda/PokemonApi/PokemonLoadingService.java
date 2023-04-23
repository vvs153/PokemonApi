package com.sda.PokemonApi;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class PokemonLoadingService {
private final FetchPokemonListUseCase fetchPokemonListUseCase;
private final PokemonApiItemRepository pokemonApiItemRepository;
private final PokemonApiNetworkRepository pokemonApiNetworkRepository;

    public PokemonLoadingService(FetchPokemonListUseCase fetchPokemonListUseCase, PokemonApiItemRepository pokemonApiItemRepository, PokemonApiNetworkRepository pokemonApiNetworkRepository) {
        this.fetchPokemonListUseCase = fetchPokemonListUseCase;
        this.pokemonApiItemRepository = pokemonApiItemRepository;
        this.pokemonApiNetworkRepository = pokemonApiNetworkRepository;
    }

    @PostConstruct
    void loadPokemonList(){
        long repoCount = pokemonApiItemRepository.count();
        if(repoCount==0){
            fetchPokemonListUseCase.execute();
        }
        else {
            PokemonList countResult = pokemonApiNetworkRepository.fetchPokemonListResult(1,0);
            int apiResponseCount = countResult.getCount();
            if(repoCount!=apiResponseCount){
                pokemonApiItemRepository.deleteAll();
                fetchPokemonListUseCase.execute();
            }
        }
    }
}
