package com.sda.PokemonApi.healthcheck.pokemon_details;

import com.sda.PokemonApi.healthcheck.PokemonApiListHealthcheck;
import com.sda.PokemonApi.healthcheck.pokemon_details.PokemonDetailsDatabaseHealthCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.CompositeHealthContributor;
import org.springframework.boot.actuate.health.HealthContributor;
import org.springframework.boot.actuate.health.NamedContributor;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

@Component("pokemondetails")
public class PokemonDetailsHealthCheck implements CompositeHealthContributor {
    private final Map<String, HealthContributor> contributorMap = new LinkedHashMap<>();

    @Autowired
    public PokemonDetailsHealthCheck(PokemonDetailsDatabaseHealthCheck pokemonDetailsDatabaseHealthCheck, PokemonApiListHealthcheck pokemonApiListHealthcheck) {
        contributorMap.put("pokemondetailsdatabase", pokemonDetailsDatabaseHealthCheck);
        contributorMap.put("pokeapilist", pokemonApiListHealthcheck);
    }
    @Override
    public Iterator<NamedContributor<HealthContributor>> iterator() {
        return contributorMap.entrySet().stream()
                .map((entry) -> NamedContributor.of(entry.getKey(), entry.getValue())).iterator();
    }

    @Override
    public HealthContributor getContributor(String name) {
        return contributorMap.get(name);
    }
}
