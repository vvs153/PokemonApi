package com.sda.PokemonApi;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonApiService {
    private final FetchPokemonListUseCase fetchPokemonListUseCase;
    private final PokemonApiItemRepository pokemonApiItemRepository;

    public PokemonApiService(FetchPokemonListUseCase fetchPokemonListUseCase, PokemonApiItemRepository pokemonApiItemRepository) {
        this.fetchPokemonListUseCase = fetchPokemonListUseCase;
        this.pokemonApiItemRepository = pokemonApiItemRepository;
    }


    public List<PokemonItemEntity> getPokemonListResult(){
        if(pokemonApiItemRepository.count()!=0){
           return pokemonApiItemRepository.findAll();
        }else{
            return fetchPokemonListUseCase.execute();
        }

    }
}
