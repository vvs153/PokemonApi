package com.sda.PokemonApi.healthcheck.pokemonlist;

import com.sda.PokemonApi.healthcheck.PokemonApiListHealthcheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.CompositeHealthContributor;
import org.springframework.boot.actuate.health.HealthContributor;
import org.springframework.boot.actuate.health.NamedContributor;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

@Component("pokemonlist")
public class PokemonListHealthCheck implements CompositeHealthContributor {

    private final Map<String, HealthContributor> contributorMap = new LinkedHashMap<>();

    @Autowired
    public PokemonListHealthCheck(PokemonDatabaseHealthCheck pokemonListDatabaseHealthCheck, PokemonApiListHealthcheck pokemonApiListHealthcheck) {
        contributorMap.put("pokemonlistdatabase", pokemonListDatabaseHealthCheck);
        contributorMap.put("pokeapilist", pokemonApiListHealthcheck);
    }

    @Override
    public HealthContributor getContributor(String name){
        return contributorMap.get(name);
    }
    @Override
    public Iterator<NamedContributor<HealthContributor>> iterator() {
        return contributorMap.entrySet().stream()
                .map((entry) -> NamedContributor.of(entry.getKey(), entry.getValue())).iterator();
    }
}
