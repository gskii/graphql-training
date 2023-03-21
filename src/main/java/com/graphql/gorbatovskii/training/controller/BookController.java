package com.graphql.gorbatovskii.training.controller;

import com.graphql.gorbatovskii.training.model.Author;
import com.graphql.gorbatovskii.training.model.Book;
import com.graphql.gorbatovskii.training.repository.AuthorRepository;
import com.graphql.gorbatovskii.training.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
public class BookController {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookController(final BookRepository bookRepository,
                          final AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @QueryMapping
    public Book bookById(final @Argument String id) {
        return this.bookRepository.getById(id);
    }

    @SchemaMapping
    public Author author(final Book book) {
        return this.authorRepository.getById(book.getAuthorId());
    }
}
