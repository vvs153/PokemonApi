package com.sda.PokemonApi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/pokemon")
public class PokemonApiController {

    private final RestTemplate restTemplate;

    public PokemonApiController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @GetMapping("/list")
    public List<PokemonList> getPokemonListResult() {
      return PokemonApiService.getPokemonListResult();

    }

}
