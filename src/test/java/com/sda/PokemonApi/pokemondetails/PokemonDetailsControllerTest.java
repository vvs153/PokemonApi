package com.sda.PokemonApi.pokemondetails;

import com.sda.PokemonApi.PokemonApiApplication;
import com.sda.PokemonApi.PokemonApiController;
import com.sda.PokemonApi.PokemonApiService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = PokemonApiApplication.class
)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
class PokemonDetailsControllerTest {
    @Autowired
    MockMvc mockMvc;


    @Test
    void given_name_of_existing_pokemon_when_GET_pokemon_details_then_pokemon_details_should_be_returned() throws Exception {
        //given
        String pokemonName = "bulbasaur";
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/pokemon/details/"+pokemonName)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.height", equalTo(7))
                )
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.weight", equalTo(69))
                )
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.image", equalTo("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"))
                )
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.types[0]", equalTo("grass"))
                )
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.types[1]", equalTo("poison"))
                )
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.abilities[0]", equalTo("overgrow"))
                )
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.abilities[1]", equalTo("chlorophyll"))
                )
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }
    @Test
    void given_name_of_not_existing_pokemon_when_GET_pokemon_details_then_404_not_found_should_be_returned() throws Exception {
        String pokemonName = "bulba";
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/pokemon/details/"+pokemonName)
                                .contentType(MediaType.APPLICATION_JSON)
                )


                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath(
                        "$",equalTo(String.format("Can't find %s", pokemonName))
                ));
    }
}