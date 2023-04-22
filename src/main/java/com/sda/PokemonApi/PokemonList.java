package com.sda.PokemonApi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PokemonList {
   private Integer count;
   private String next;
   private String previous;
   private List<PokemonListItem> results;


}
@Data
@NoArgsConstructor
@AllArgsConstructor
class PokemonListItem{
   private String name;
   private String url;
}
