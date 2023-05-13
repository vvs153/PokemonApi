package com.sda.PokemonApi;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FetchPokemonListUseCase {
    private final PokemonApiItemRepository pokemonApiItemRepository;
    private final PokemonApiNetworkRepository pokemonApiNetworkRepository;
    private final PokemonListItemMapper pokemonListItemMapper;

    public FetchPokemonListUseCase(PokemonApiItemRepository pokemonApiItemRepository, PokemonApiNetworkRepository pokemonApiNetworkRepository, PokemonListItemMapper pokemonListItemMapper) {
        this.pokemonApiItemRepository = pokemonApiItemRepository;
        this.pokemonApiNetworkRepository = pokemonApiNetworkRepository;
        this.pokemonListItemMapper = pokemonListItemMapper;
    }

    List<PokemonItemEntity> execute(){
        List<PokemonListItem> results = new ArrayList<>();
        PokemonList result;
        int limit = 100;
        int offset = 0;
        do{
            result = pokemonApiNetworkRepository.fetchPokemonListResult(limit,offset);
            List<PokemonListItem> _results = result.getResults();
            if (_results != null) {
                results.addAll(result.getResults());
            }
            offset +=limit;
        } while (result.getNext() != null);
        List<PokemonItemEntity> pokemonItemEntityList =
                results.stream().map(pokemonListItemMapper::toEntity).toList();
        return pokemonApiItemRepository.saveAll(pokemonItemEntityList);
    }
}
