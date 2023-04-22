package com.sda.PokemonApi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
interface PokemonApiItemRepository extends JpaRepository<PokemonItemEntity, Long> {


}
