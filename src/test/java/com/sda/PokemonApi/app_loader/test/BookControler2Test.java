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
import java.util.Collections;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BookControler.class)
public class BookControler2Test {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    BookService bookService;

    @Test
    void given_empty_book_list_when_send_GET_books_request_then_empty_book_list_should_be_returned() throws Exception {
        //given
        Mockito.when(bookService.getBooks()).thenReturn(Collections.emptyList());
        //when
        //then
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/books")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$", hasSize(0))
                );

    }

    @Test
    void given_empty_book_list_when_send_Post_request_to_add_book_then_book_should_be_added() throws Exception {
        //given
        Mockito.when(bookService.addBook(new Book("Book test"))).thenReturn(new Book(20L,"Book test"));
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "text": "Book test"
                        }
                        """))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.text", equalTo("Book test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", equalTo(20)));
    }
    }

