package com.sda.PokemonApi.pokemon_details;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
interface PokemonDetailsRepository extends JpaRepository<PokemonDetailsEntity, String> {
}
