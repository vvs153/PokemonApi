package com.sda.PokemonApi;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class PokemonLoadingService {
private final FetchPokemonListUseCase fetchPokemonListUseCase;
private final PokemonApiItemRepository pokemonApiItemRepository;
private final PokemonApiNetworkRepository pokemonApiNetworkRepository;

    public PokemonLoadingService(FetchPokemonListUseCase fetchPokemonListUseCase,
                                 PokemonApiItemRepository pokemonApiItemRepository,
                                 PokemonApiNetworkRepository pokemonApiNetworkRepository) {
        this.fetchPokemonListUseCase = fetchPokemonListUseCase;
        this.pokemonApiItemRepository = pokemonApiItemRepository;
        this.pokemonApiNetworkRepository = pokemonApiNetworkRepository;
    }


    @Scheduled(fixedRate = 3600000,initialDelay = 100)
    void loadPokemonList() {

            long repoCount = pokemonApiItemRepository.count();
            if (repoCount == 0) {
                fetchPokemonListUseCase.execute();
            } else {
                PokemonList countResult = pokemonApiNetworkRepository.fetchPokemonListResult(1, 0);
                int apiResponseCount = countResult.getCount();
                if (repoCount != apiResponseCount) {
                    pokemonApiItemRepository.deleteAll();
                    fetchPokemonListUseCase.execute();
                }
            }

    }


}
