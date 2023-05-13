package com.sda.PokemonApi.app_loader.test;


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

import java.util.Arrays;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;


@ExtendWith(SpringExtension.class)
@WebMvcTest(BookControler.class)
class BookControlerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    BookService bookService;

    @Test
    void given_not_empty_book_list_when_send_GET_books_request_then_book_list_should_be_returned() throws Exception {
        //given
        Mockito.when(bookService.getBooks()).thenReturn(Arrays.asList(
                new Book(2L, "test"),
                new Book(3L, "kot")
        ));

        //when
        //then
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/books")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$", hasSize(2))
                )
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$[0].id", equalTo(2))
                )
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$[1].id", equalTo(3))
                );
    }
}
