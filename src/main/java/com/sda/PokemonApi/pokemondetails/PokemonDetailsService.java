package com.sda.PokemonApi.pokemondetails;

import com.sda.PokemonApi.exception.NoPokemonFoundException;
import com.sda.PokemonApi.PokemonApiItemRepository;
import com.sda.PokemonApi.PokemonItemEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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
                pokemonApiItemRepository.findByNameIgnoreCase(pokemonName)
                        .orElseThrow(()->new NoPokemonFoundException(pokemonName));
        PokemonDetails pokemonDetails =
                pokemonApiDetailsNetworkRepository.fetchPokemonDetailsResult(pokemonItemEntity.getId());
        return pokemonDetailsMapper.toEntity(pokemonDetails);
    }

    List<PokemonDetailsEntity> getPokemonDetailsUrl(List<String> pokemonNames){
      return   pokemonNames.stream().map(pokemonName ->{
          try {
                  return getPokemonDetailsUrl(pokemonName);
              } catch(NoPokemonFoundException e){
                    return null;
              }
      }).toList();

    }


}
