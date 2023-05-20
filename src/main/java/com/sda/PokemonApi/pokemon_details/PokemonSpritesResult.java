package com.sda.PokemonApi.pokemon_details;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PokemonSpritesResult {
    private PokemonOther other;

}
@Data
@AllArgsConstructor
@NoArgsConstructor
class PokemonOther{
    @JsonProperty("official-artwork")
    private PokemonOfficialArtwork officialArtwork;
}
@Data
@AllArgsConstructor
@NoArgsConstructor
class PokemonOfficialArtwork{
    @JsonProperty("front_default")
    private String frontDefault;
    @JsonProperty("front_shiny")
    private String frontShiny;
}