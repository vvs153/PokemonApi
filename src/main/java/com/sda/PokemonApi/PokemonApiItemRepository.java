package com.sda.PokemonApi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface PokemonApiItemRepository extends JpaRepository<PokemonItemEntity, Long> {
    Optional<PokemonItemEntity> findByName(String name);

}
