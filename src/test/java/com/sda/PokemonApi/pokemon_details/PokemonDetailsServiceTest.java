package com.sda.PokemonApi.pokemon_details;

import com.sda.PokemonApi.PokemonApiItemRepository;
import com.sda.PokemonApi.PokemonItemEntity;
import com.sda.PokemonApi.exception.NoPokemonFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PokemonDetailsServiceTest {


    @Test
    void given_pokemon_details_not_stored_in_db_when_get_details_then_network_call_should_be_proceed(){
       //given
        PokemonApiItemRepository pokemonApiItemRepository = Mockito.mock(PokemonApiItemRepository.class);
        PokemonApiDetailsNetworkRepository pokemonApiDetailsNetworkRepository = Mockito.mock(PokemonApiDetailsNetworkRepository.class);
        PokemonDetailsMapper pokemonDetailsMapper = Mockito.mock(PokemonDetailsMapper.class);
        PokemonDetailsRepository pokemonDetailsRepository = Mockito.mock(PokemonDetailsRepository.class);
        PokemonDetailsService pokemonDetailsService = new PokemonDetailsService(pokemonApiItemRepository,pokemonApiDetailsNetworkRepository,pokemonDetailsMapper,pokemonDetailsRepository);
        String pokemonName = "Bulbasaur";
        PokemonDetailsEntity pokemonDetailsEntity = Mockito.mock(PokemonDetailsEntity.class);
        PokemonItemEntity pokemonItemEntity = new PokemonItemEntity(1L, pokemonName);
        Mockito.when(pokemonApiItemRepository.findByNameIgnoreCase(pokemonName)).thenReturn(Optional.of(pokemonItemEntity));
        Mockito.when(pokemonDetailsMapper.toEntity(Mockito.any())).thenReturn(pokemonDetailsEntity);

        //when
        pokemonDetailsService.getPokemonDetailsUrl(pokemonName);

        //then
        Mockito.verify(pokemonApiDetailsNetworkRepository).fetchPokemonDetailsResult(1L);
        Mockito.verify(pokemonDetailsRepository).save(pokemonDetailsEntity);
    }
    @Test
    void given_pokemon_not_exist_in_db_when_get_details_then_details_not_be_provided() {
        //given
        PokemonApiItemRepository pokemonApiItemRepository = Mockito.mock(PokemonApiItemRepository.class);
        PokemonApiDetailsNetworkRepository pokemonApiDetailsNetworkRepository = Mockito.mock(PokemonApiDetailsNetworkRepository.class);
        PokemonDetailsMapper pokemonDetailsMapper = Mockito.mock(PokemonDetailsMapper.class);
        PokemonDetailsRepository pokemonDetailsRepository = Mockito.mock(PokemonDetailsRepository.class);
        PokemonDetailsService pokemonDetailsService = new PokemonDetailsService(pokemonApiItemRepository,pokemonApiDetailsNetworkRepository,pokemonDetailsMapper,pokemonDetailsRepository);
        String pokemonName = "Bulbasaur";
        Mockito.when(pokemonDetailsRepository.findById(pokemonName)).thenReturn(Optional.empty());

        //when
        //then

        assertThrows(NoPokemonFoundException.class, () -> {
            pokemonDetailsService.getPokemonDetailsUrl(pokemonName);
        });

        //then
        Mockito.verify(pokemonDetailsRepository, Mockito.never()).findById(Mockito.any());
        Mockito.verify(pokemonApiDetailsNetworkRepository, Mockito.never()).fetchPokemonDetailsResult(1L);
        Mockito.verify(pokemonDetailsRepository, Mockito.never()).save(Mockito.any());
    }
    @Test
    void given_pokemon_details_stored_in_db_when_get_details_then_details_should_be_provided_from_db(){
        PokemonApiItemRepository pokemonApiItemRepository = Mockito.mock(PokemonApiItemRepository.class);
        PokemonApiDetailsNetworkRepository pokemonApiDetailsNetworkRepository = Mockito.mock(PokemonApiDetailsNetworkRepository.class);
        PokemonDetailsMapper pokemonDetailsMapper = Mockito.mock(PokemonDetailsMapper.class);
        PokemonDetailsRepository pokemonDetailsRepository = Mockito.mock(PokemonDetailsRepository.class);
        PokemonDetailsService pokemonDetailsService = new PokemonDetailsService(pokemonApiItemRepository,pokemonApiDetailsNetworkRepository,pokemonDetailsMapper,pokemonDetailsRepository);
        String pokemonName = "Bulbasaur";
        PokemonDetailsEntity pokemonDetailsEntity = Mockito.mock(PokemonDetailsEntity.class);
        PokemonItemEntity pokemonItemEntity = new PokemonItemEntity(1L, pokemonName);
        Mockito.when(pokemonApiItemRepository.findByNameIgnoreCase(pokemonName)).thenReturn(Optional.of(pokemonItemEntity));
        Mockito.when(pokemonDetailsMapper.toEntity(Mockito.any())).thenReturn(pokemonDetailsEntity);

        //when
        pokemonDetailsService.getPokemonDetailsUrl(pokemonName);

        //then
        Mockito.verify(pokemonApiDetailsNetworkRepository).fetchPokemonDetailsResult(1L);
        Mockito.verify(pokemonDetailsRepository).save(pokemonDetailsEntity);

    }


}