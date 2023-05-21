package com.sda.PokemonApi.healthcheck;

import com.sda.PokemonApi.PokemonList;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthContributor;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;

@Component("pokeapilist")
public class PokemonApiListHealthcheck implements HealthContributor, HealthIndicator {
    private final RestTemplate restTemplate;
    private final String url;

    public PokemonApiListHealthcheck(RestTemplate restTemplate, @Value("${pokeapi.url}") String url) {
        this.restTemplate = restTemplate;
        this.url = "https://pokeapiu.co/api/v2/pokemon?limit=%d&offset=%d";
    }

    @Override
    public Health health() {
        try(Socket socket = new Socket()){
            socket.connect(new InetSocketAddress(new URL(String.format(url,0,1)).getHost(),80));
        } catch (Exception e){
            Health.down()
                    .withDetail("error", e.toString())
                    .build();
        }
        return Health.up().build();
     /*   try {
            restTemplate.getForObject(String.format(url, 0, 1), PokemonList.class);
        } catch (HttpServerErrorException e) {
            Health.down().build();
        }
        return Health.up().build();

      */
    }
    }

