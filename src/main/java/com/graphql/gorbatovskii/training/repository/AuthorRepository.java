package com.graphql.gorbatovskii.training.repository;

import com.graphql.gorbatovskii.training.model.Author;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_SINGLETON;

@Component
@Scope(SCOPE_SINGLETON)
public class AuthorRepository {

    private final List<Author> authors;

    public AuthorRepository() {
        this.authors = Collections.synchronizedList(new ArrayList<>(
            Arrays.asList(
                new Author("author-1", "Joanne", "Rowling"),
                new Author("author-2", "Herman", "Melville"),
                new Author("author-3", "Anne", "Rice")
            )));
    }

    public Author getById(String id) {
        return this.authors.stream().filter(author -> author.getId().equals(id)).findFirst().orElse(null);
    }
}
