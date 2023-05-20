package com.sda.PokemonApi.pokemon_details;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PokemonDetailsEntity {
    @Id
    private String name;
    private Integer height;
    private Integer weight;

    private String image;
    @ElementCollection
    private List<String> types;
    @ElementCollection
    private List<String> abilities;


}
