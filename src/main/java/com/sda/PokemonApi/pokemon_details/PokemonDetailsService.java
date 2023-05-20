package com.sda.PokemonApi.pokemon_details;

import com.sda.PokemonApi.exception.NoPokemonFoundException;
import com.sda.PokemonApi.PokemonApiItemRepository;
import com.sda.PokemonApi.PokemonItemEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PokemonDetailsService{
    private final PokemonApiItemRepository pokemonApiItemRepository;
    private final PokemonApiDetailsNetworkRepository pokemonApiDetailsNetworkRepository;
    private final PokemonDetailsMapper pokemonDetailsMapper;
    private final PokemonDetailsRepository pokemonDetailsRepository;

    public PokemonDetailsService(PokemonApiItemRepository pokemonApiItemRepository, PokemonApiDetailsNetworkRepository pokemonApiDetailsNetworkRepository, PokemonDetailsMapper pokemonDetailsMapper, PokemonDetailsRepository pokemonDetailsRepository) {
        this.pokemonApiItemRepository = pokemonApiItemRepository;
        this.pokemonApiDetailsNetworkRepository = pokemonApiDetailsNetworkRepository;
        this.pokemonDetailsMapper = pokemonDetailsMapper;
        this.pokemonDetailsRepository = pokemonDetailsRepository;
    }


    PokemonDetailsEntity getPokemonDetailsUrl(String pokemonName) {
        PokemonItemEntity pokemonItemEntity = pokemonApiItemRepository.findByNameIgnoreCase(pokemonName)
        .orElseThrow(()-> new NoPokemonFoundException(pokemonName));

        return  pokemonDetailsRepository.findById(pokemonName)
                .orElseGet(()-> {
            PokemonDetails pokemonDetails =
                    pokemonApiDetailsNetworkRepository
                            .fetchPokemonDetailsResult(pokemonItemEntity.getId());
            PokemonDetailsEntity entity = pokemonDetailsMapper.toEntity(pokemonDetails);
            return pokemonDetailsRepository.save(entity);
        });
    }

    List<PokemonDetailsEntity> getPokemonDetailsUrl(List<String> pokemonNames){
      return   pokemonNames.stream().map(pokemonName ->{
          try {
                  return getPokemonDetailsUrl(pokemonName);
              } catch(NoPokemonFoundException e){
                    return null;
              }
      })
              .filter(Objects::nonNull)
              .toList();

    }


}
