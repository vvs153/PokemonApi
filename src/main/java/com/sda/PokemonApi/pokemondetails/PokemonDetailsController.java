package com.sda.PokemonApi.pokemondetails;

import com.sda.PokemonApi.exception.NoPokemonFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pokemon/details")
public class PokemonDetailsController {
    private final PokemonDetailsService pokemonDetailsService;

    public PokemonDetailsController(PokemonDetailsService pokemonDetailsService) {
        this.pokemonDetailsService = pokemonDetailsService;
    }

    @GetMapping("/{pokemonName}")
    PokemonDetailsEntity getPokemonDetails(@PathVariable String pokemonName) {
        return pokemonDetailsService.getPokemonDetailsUrl(pokemonName);
    }

    @GetMapping
    List<PokemonDetailsEntity> getPokemonDetails(List<String> pokemonNames){
        return pokemonDetailsService.getPokemonDetailsUrl(pokemonNames);
    }
    @ExceptionHandler(NoPokemonFoundException.class)
    ResponseEntity<Object> maptoNoPokemonFoundException(NoPokemonFoundException e){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }
    }

