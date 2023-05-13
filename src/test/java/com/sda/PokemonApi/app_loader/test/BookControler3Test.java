package com.sda.PokemonApi.app_loader.test;

import com.sda.PokemonApi.PokemonApiApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = PokemonApiApplication.class
)
@AutoConfigureMockMvc
class BookControler3Test {
@Autowired
    private MockMvc mockMvc;

    @Test
    void given_not_empty_book_list_when_send_GET_books_request_then_book_list_should_be_returned() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "text": "Book test"
                        }
                        """)
                ).andExpect(MockMvcResultMatchers.jsonPath("$.id", equalTo(20)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.text", equalTo("Book test")));

        mockMvc.perform(MockMvcRequestBuilders.post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "text": "Book test 1"
                        }
                        """)
                ).andExpect(MockMvcResultMatchers.jsonPath("$.id", equalTo(20)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.text", equalTo("Book test 1")));

        mockMvc.perform(MockMvcRequestBuilders.get("/books")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)));
    }


}