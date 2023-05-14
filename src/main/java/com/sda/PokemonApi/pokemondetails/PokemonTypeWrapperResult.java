package com.sda.PokemonApi.pokemondetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PokemonTypeWrapperResult {
    private int slot;
    private PokemonType type;

}
@Data
@NoArgsConstructor
@AllArgsConstructor
class PokemonType {
    private String name;
    private String url;
}
