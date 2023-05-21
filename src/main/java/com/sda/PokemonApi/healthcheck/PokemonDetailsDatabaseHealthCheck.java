package com.sda.PokemonApi.healthcheck;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthContributor;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Component("pokemondetailsdatabase")
public class PokemonDetailsDatabaseHealthCheck  implements HealthContributor, HealthIndicator {
    private final DataSource source;

    public PokemonDetailsDatabaseHealthCheck(DataSource source) {
        this.source = source;
    }

    @Override
    public Health health() {
        try(Connection connection = source.getConnection()){
            Statement statement = connection.createStatement();
            statement.execute("SELECT COUNT(*) FORM pokemon_details_entity");
        } catch (SQLException e) {
            return Health.down().build();
        }
        return Health.up().build();
    }
    }

