package com.graphql.gorbatovskii.training.repository;

import com.graphql.gorbatovskii.training.model.Book;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_SINGLETON;

@Component
@Scope(SCOPE_SINGLETON)
public class BookRepository {

    private final List<Book> books;

    public BookRepository() {
        this.books = Collections.synchronizedList(new ArrayList<>(
            Arrays.asList(
                new Book("book-1", "Harry Potter and the Philosopher's Stone", 223, "author-1"),
                new Book("book-2", "Moby Dick", 635, "author-2"),
                new Book("book-3", "Interview with the vampire", 371, "author-3")
            )
        ));
    }

    public Book getById(String id) {
        return this.books.stream().filter(book -> book.getId().equals(id)).findFirst().orElse(null);
    }
}
