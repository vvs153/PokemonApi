package com.sda.PokemonApi;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PokemonApiServiceTest {
@Test
    void given_non_empty_repo_when_requesting_pokemon_list_then_fetching_from_network_should_be_omitted(){
       //given
        FetchPokemonListUseCase useCase = Mockito.mock(FetchPokemonListUseCase.class);
        PokemonApiItemRepository itemRepository = Mockito.mock(PokemonApiItemRepository.class);
        PokemonApiService service = new PokemonApiService(useCase,itemRepository);
        Mockito.when(itemRepository.count()).thenReturn(1L);
        List<PokemonItemEntity> expectedResult = List.of(new PokemonItemEntity(1L, "Test"));
        Mockito.when(itemRepository.findAll()).thenReturn(expectedResult);
        //when
        List<PokemonItemEntity> results = service.getPokemonListResult();
        //then
        assertEquals(expectedResult,results);
        Mockito.verify(useCase, Mockito.never()).execute();
    }
    @Test
    void given_empty_rep_when_requesting_pokemon_list_then_fetching_from_network_should_be_omitted(){
        //given
        FetchPokemonListUseCase useCase = Mockito.mock(FetchPokemonListUseCase.class);
        PokemonApiItemRepository itemRepository = Mockito.mock(PokemonApiItemRepository.class);
        PokemonApiService service = new PokemonApiService(useCase,itemRepository);
        List<PokemonItemEntity> expectedResult = List.of(new PokemonItemEntity(1L, "Test"));
        Mockito.when(itemRepository.count()).thenReturn(0L);
        Mockito.when(useCase.execute()).thenReturn(expectedResult);
        //when
        List<PokemonItemEntity> results = service.getPokemonListResult();
        //then
        assertEquals(expectedResult, results);
        Mockito.verify(itemRepository, Mockito.never()).findAll();
    }
}