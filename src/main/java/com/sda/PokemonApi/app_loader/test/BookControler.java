package com.sda.PokemonApi.app_loader.test;

import org.springframework.web.bind.annotation.*;

import java.util.List;


    @RequestMapping("/books")
    @RestController
    public class BookControler {

        private final BookService bookService;

        public BookControler(BookService bookService) {
            this.bookService = bookService;
        }

        @GetMapping
        public List<Book> getBooks() {
            return bookService.getBooks();
        }

        @PostMapping
        public Book addBook(@RequestBody Book book) {
            return bookService.addBook(book);
        }
    }
