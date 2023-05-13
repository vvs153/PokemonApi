package com.sda.PokemonApi.app_loader.test;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
public class BookService {

    private final List<Book> books = new ArrayList<>();

    public List<Book> getBooks() {
        return books;
    }

    public Book addBook(Book book) {
        book.setId(20L);
        books.add(book);
        return book;
    }
}

class Book {
        private Long id;
        private String text;

        public Book(Long id, String text) {
            this.id = id;
            this.text = text;
        }

        public Book(String text) {
            this.text = text;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Book book = (Book) o;
            return Objects.equals(id, book.id) && Objects.equals(text, book.text);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, text);
        }
}
