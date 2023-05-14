package com.sda.PokemonApi.pokemondetails;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PokemonAbilityWrapperResult {
    private PokemonAbilityResult ability;
    @JsonProperty("is_hidden")
    private boolean isHidden;
    private int slot;


}
@Data
@AllArgsConstructor
@NoArgsConstructor
class PokemonAbilityResult{
    private String name;
    private String url;

}
