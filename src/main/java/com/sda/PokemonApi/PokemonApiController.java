package com.sda.PokemonApi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pokemon")
public class PokemonApiController {



   private final PokemonApiService pokemonApiService;

    public PokemonApiController(PokemonApiService pokemonApiService) {
        this.pokemonApiService = pokemonApiService;
    }


    @GetMapping("/list")
    public List<PokemonItemEntity> getPokemonListResult() {
      return pokemonApiService.getPokemonListResult();

    }

}
