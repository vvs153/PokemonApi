package com.sda.PokemonApi;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class PokemonLoadingServiceTest {
    @Test
    void given_empty_pokemon_db_when_requesting_pokemon_list_then_results_network_should_be_returned(){
        //given
        FetchPokemonListUseCase fetchPokemonListUseCase = Mockito.mock(FetchPokemonListUseCase.class);
        PokemonApiNetworkRepository networkRepository = Mockito.mock(PokemonApiNetworkRepository.class);
        PokemonApiItemRepository itemRepository = Mockito.mock(PokemonApiItemRepository.class);
        PokemonLoadingService pokemonLoadingService = new PokemonLoadingService(fetchPokemonListUseCase,itemRepository,networkRepository);
        Mockito.when(itemRepository.count()).thenReturn(0L);

        //when
        pokemonLoadingService.loadPokemonList();
        //then
        Mockito.verify(fetchPokemonListUseCase,Mockito.times(1)).execute();
    }

@Test
    void given_non_empty_pokemon_db_and_difference_between_db_and_network_when_fetching_list_than_db_should_be_cleared_and_fetch_should_happen(){
    //given
    FetchPokemonListUseCase fetchPokemonListUseCase = Mockito.mock(FetchPokemonListUseCase.class);
    PokemonApiNetworkRepository networkRepository = Mockito.mock(PokemonApiNetworkRepository.class);
    PokemonApiItemRepository itemRepository = Mockito.mock(PokemonApiItemRepository.class);
    PokemonLoadingService pokemonLoadingService = new PokemonLoadingService(fetchPokemonListUseCase,itemRepository,networkRepository);
    PokemonList pokemonList =new PokemonList();
    pokemonList.setCount(20);
    Mockito.when(itemRepository.count()).thenReturn(10L);
    Mockito.when(networkRepository.fetchPokemonListResult(1, 0)).thenReturn(pokemonList);
    //when
    pokemonLoadingService.loadPokemonList();
    //then
    Mockito.verify(itemRepository).deleteAll();
    Mockito.verify(fetchPokemonListUseCase).execute();
}

@Test
void given_empty_pokemon_db_and_no_difference_between_db_and_network_when_fetching_list_than_do_nothing(){
    //given
    FetchPokemonListUseCase fetchPokemonListUseCase = Mockito.mock(FetchPokemonListUseCase.class);
    PokemonApiNetworkRepository networkRepository = Mockito.mock(PokemonApiNetworkRepository.class);
    PokemonApiItemRepository itemRepository = Mockito.mock(PokemonApiItemRepository.class);
    PokemonLoadingService pokemonLoadingService = new PokemonLoadingService(fetchPokemonListUseCase,itemRepository,networkRepository);
    PokemonList pokemonList =new PokemonList();
    pokemonList.setCount(10);
    Mockito.when(itemRepository.count()).thenReturn(10L);
    Mockito.when(networkRepository.fetchPokemonListResult(1, 0)).thenReturn(pokemonList);
    //when
    pokemonLoadingService.loadPokemonList();
    //then
    Mockito.verify(fetchPokemonListUseCase, Mockito.never()).execute();

    }
}