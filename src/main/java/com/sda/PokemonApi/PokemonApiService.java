package com.sda.PokemonApi;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PokemonApiService {

    private final PokemonApiNetworkRepository pokemonApiNetworkRepository;
    private final PokemonApiItemRepository pokemonApiItemRepository;
    private final PokemonListItemMapper pokemonListItemMapper;

    public PokemonApiService(PokemonApiNetworkRepository pokemonApiNetworkRepository, PokemonApiItemRepository pokemonApiItemRepository, PokemonListItemMapper pokemonListItemMapper) {
        this.pokemonApiNetworkRepository = pokemonApiNetworkRepository;
        this.pokemonApiItemRepository = pokemonApiItemRepository;
        this.pokemonListItemMapper = pokemonListItemMapper;
    }


    public List<PokemonItemEntity> getPokemonListResult(){
        List<PokemonListItem> results = new ArrayList<>();
        PokemonList result;
        int limit = 100;
        int offset = 0;
        do{
            result = pokemonApiNetworkRepository.fetchPokemonListResult(limit,offset);
            results.addAll(result.getResults());
            offset +=limit;
        } while (result.getNext() != null);
        List<PokemonItemEntity> pokemonItemEntityList =
                results.stream().map(pokemonListItemMapper::toEntity).toList();
        return pokemonApiItemRepository.saveAll(pokemonItemEntityList);
    }
}
