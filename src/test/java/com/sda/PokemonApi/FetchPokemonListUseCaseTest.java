package com.sda.PokemonApi;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FetchPokemonListUseCaseTest {

    @Test
    void given_network_api_when_requesting_pokemon_list_then_pokemon_list_should_be_mapped_and_saved_in_repo(){
        //given
        PokemonApiItemRepository itemRepository = Mockito.mock(PokemonApiItemRepository.class);
        PokemonApiNetworkRepository networkRepository = Mockito.mock(PokemonApiNetworkRepository.class);
        PokemonListItemMapper mapper = Mockito.mock(PokemonListItemMapper.class);
        FetchPokemonListUseCase useCase = new FetchPokemonListUseCase(itemRepository,networkRepository,mapper);
        PokemonListItem pokemonListItem = Mockito.mock(PokemonListItem.class);
        PokemonList pokemonList = Mockito.mock(PokemonList.class);
        PokemonItemEntity pokemonItemEntity = Mockito.mock(PokemonItemEntity.class);
        Mockito.when(pokemonList.getNext()).thenReturn(null);
        Mockito.when(pokemonList.getResults()).thenReturn(null);
        Mockito.when(networkRepository.fetchPokemonListResult(100,0)).thenReturn(pokemonList);
        Mockito.when(mapper.toEntity(pokemonListItem)).thenReturn(pokemonItemEntity);
        Mockito.when(itemRepository.saveAll(Collections.singletonList(pokemonItemEntity))).thenReturn(Collections.singletonList(pokemonItemEntity));
        //when
        List<PokemonItemEntity> result = useCase.execute();
        //then
        assertEquals(Collections.singletonList(pokemonItemEntity),result);

    }

    @Test
    void given_network_api_when_requesting_empty_pokemon_list_then_pokemon_list_should_be_mapped_and_saved_in_repo(){
        //given
        PokemonApiItemRepository itemRepository = Mockito.mock(PokemonApiItemRepository.class);
        PokemonApiNetworkRepository networkRepository = Mockito.mock(PokemonApiNetworkRepository.class);
        PokemonListItemMapper mapper = Mockito.mock(PokemonListItemMapper.class);
        FetchPokemonListUseCase useCase = new FetchPokemonListUseCase(itemRepository,networkRepository,mapper);
        Mockito.when(networkRepository.fetchPokemonListResult(100,0)).thenReturn(new PokemonList());
        Mockito.when(itemRepository.saveAll(Collections.emptyList())).thenReturn(Collections.emptyList());

        //when
        List<PokemonItemEntity> result = useCase.execute();
        //then
        assertTrue(result.isEmpty());

    }

}