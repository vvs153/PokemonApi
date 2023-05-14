package com.sda.PokemonApi.pokemondetails;

import com.sda.PokemonApi.exception.NoPokemonFoundException;
import com.sda.PokemonApi.PokemonApiItemRepository;
import com.sda.PokemonApi.PokemonItemEntity;
import org.springframework.stereotype.Service;

@Service
public class PokemonDetailsService{
    private final PokemonApiItemRepository pokemonApiItemRepository;
    private final PokemonApiDetailsNetworkRepository pokemonApiDetailsNetworkRepository;
    private final PokemonDetailsMapper pokemonDetailsMapper;

    public PokemonDetailsService(PokemonApiItemRepository pokemonApiItemRepository, PokemonApiDetailsNetworkRepository pokemonApiDetailsNetworkRepository, PokemonDetailsMapper pokemonDetailsMapper) {
        this.pokemonApiItemRepository = pokemonApiItemRepository;
        this.pokemonApiDetailsNetworkRepository = pokemonApiDetailsNetworkRepository;
        this.pokemonDetailsMapper = pokemonDetailsMapper;
    }


    PokemonDetailsEntity getPokemonDetailsUrl(String pokemonName) {
        PokemonItemEntity pokemonItemEntity =
                pokemonApiItemRepository.findByName(pokemonName)
                        .orElseThrow(()->new NoPokemonFoundException(pokemonName));
        PokemonDetails pokemonDetails =
                pokemonApiDetailsNetworkRepository.fetchPokemonDetailsResult(pokemonItemEntity.getId());
        return pokemonDetailsMapper.toEntity(pokemonDetails);
    }


}
