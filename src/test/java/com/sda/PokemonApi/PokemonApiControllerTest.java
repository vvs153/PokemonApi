package com.sda.PokemonApi;

import com.sda.PokemonApi.app_loader.test.BookControler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.util.NestedServletException;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PokemonApiController.class)
class PokemonApiControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    PokemonApiService pokemonApiService;

    @Test
    void given_not_empty_pokemon_list_when_send_GET_pokemon_request_then_pokemon_list_should_be_returned() throws Exception {
        //given
        Mockito.when(pokemonApiService.getPokemonListResult()).thenReturn(Arrays.asList(
                new PokemonItemEntity(4L,"kek"),
                new PokemonItemEntity(5L, "aaa")
        ));
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/pokemon/list")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$", hasSize(2))
                )
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$[0].id", equalTo(4))
                )
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$[1].id", equalTo(5))
                )
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$[1].name", equalTo("aaa"))
                )
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$[0].name", equalTo("kek"))
                )
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    void given_empty_pokemon_list_when_send_GET_pokemon_request_then_empty_pokemon_list_should_be_returned() throws Exception {
        //given
        Mockito.when(pokemonApiService.getPokemonListResult()).thenReturn(Collections.emptyList());
        //when
        //then
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/pokemon/list")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$", hasSize(0))
                )
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

    }
    @Test
    void given_pokemon_list_when_send_GET_pokemon_request_then_500_type_error_should_be_returned() throws Exception {
        //given
        Mockito.when(pokemonApiService.getPokemonListResult()).thenThrow(IllegalArgumentException.class);
        //when
        //then
        assertThrows(NestedServletException.class, ()-> {
            mockMvc.perform(
                            MockMvcRequestBuilders.get("/pokemon/list")
                                    .contentType(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(MockMvcResultMatchers.status().is5xxServerError());
        });
    }
}