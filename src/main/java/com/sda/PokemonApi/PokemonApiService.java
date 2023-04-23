package com.sda.PokemonApi;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonApiService {
    private final FetchPokemonListUseCase fetchPokemonListUseCase;

    public PokemonApiService(FetchPokemonListUseCase fetchPokemonListUseCase) {
        this.fetchPokemonListUseCase = fetchPokemonListUseCase;
    }


    public List<PokemonItemEntity> getPokemonListResult(){

        return fetchPokemonListUseCase.execute();
    }
}
