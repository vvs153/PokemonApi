package com.sda.PokemonApi;

import org.springframework.data.crossstore.ChangeSetPersister;

public class NoPokemonFoundException extends RuntimeException {
   public NoPokemonFoundException(String pokemonName){
        super(String.format("Can't find %s", pokemonName));
    }
}
