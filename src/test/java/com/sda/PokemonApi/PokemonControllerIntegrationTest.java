package com.sda.PokemonApi;

import org.junit.jupiter.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.hasSize;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = PokemonApiApplication.class
)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
class PokemonControllerIntegrationTest {
    @Autowired
    private PokemonApiItemRepository itemRepository;
    @Autowired
    private MockMvc mockMvc;
    @Test
    void given_app_started_when_send_GET_pokemon_request_then_pokemon_list_should_be_returned() throws Exception {
        //given
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/pokemon/list")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.not(Matchers.emptyArray())));

        Assertions.assertTrue(itemRepository.count()!=0);
    }
}