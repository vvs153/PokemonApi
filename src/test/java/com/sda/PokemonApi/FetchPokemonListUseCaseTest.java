package com.sda.PokemonApi;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
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
        Mockito.when(pokemonList.getResults()).thenReturn(Collections.singletonList(pokemonListItem));
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
    @Test
    void given_network_api_when_fetching_pokemon_list_including_pagination_then_pokemon_list_should_be_mapped_to_entities_and_saved_in_repo() {
        //given
        PokemonApiNetworkRepository pokeApiListNetworkRepository = Mockito.mock(PokemonApiNetworkRepository.class);
        PokemonApiItemRepository pokemonListItemRepository = Mockito.mock(PokemonApiItemRepository.class);
        PokemonListItemMapper pokemonListItemMapper = Mockito.mock(PokemonListItemMapper.class);
        FetchPokemonListUseCase fetchPokemonListUseCase = new FetchPokemonListUseCase(
                pokemonListItemRepository,
                pokeApiListNetworkRepository,
                pokemonListItemMapper
        );
        PokemonListItem pokeApiListItemResult1 = Mockito.mock(PokemonListItem.class);
        PokemonList pokeApiListResult1 = Mockito.mock(PokemonList.class);

        PokemonListItem pokeApiListItemResult2 = Mockito.mock(PokemonListItem.class);
        PokemonList pokeApiListResult2 = Mockito.mock(PokemonList.class);

        Mockito.when(pokeApiListResult1.getNext()).thenReturn("test");
        Mockito.when(pokeApiListResult1.getResults()).thenReturn(Collections.singletonList(pokeApiListItemResult1));

        Mockito.when(pokeApiListResult2.getNext()).thenReturn(null);
        Mockito.when(pokeApiListResult2.getResults()).thenReturn(Collections.singletonList(pokeApiListItemResult2));


        PokemonItemEntity pokemonListItemEntity1 = Mockito.mock(PokemonItemEntity.class);
        PokemonItemEntity pokemonListItemEntity2 = Mockito.mock(PokemonItemEntity.class);
        List<PokemonItemEntity> expectedResults = Arrays.asList(pokemonListItemEntity1, pokemonListItemEntity2);

        Mockito.when(pokeApiListNetworkRepository.fetchPokemonListResult(100, 0)).thenReturn(pokeApiListResult1);
        Mockito.when(pokeApiListNetworkRepository.fetchPokemonListResult(100, 100)).thenReturn(pokeApiListResult2);

        Mockito.when(pokemonListItemMapper.toEntity(pokeApiListItemResult1)).thenReturn(pokemonListItemEntity1);
        Mockito.when(pokemonListItemMapper.toEntity(pokeApiListItemResult2)).thenReturn(pokemonListItemEntity2);

        Mockito.when(pokemonListItemRepository.saveAll(expectedResults)).thenReturn(expectedResults);

        //when
        List<PokemonItemEntity> result = fetchPokemonListUseCase.execute();

        //then
        assertEquals(expectedResults, result);
    }

}