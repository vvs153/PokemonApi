package com.sda.PokemonApi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
@Configuration
public class NetworkConfiguration {
    @Bean
    RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
