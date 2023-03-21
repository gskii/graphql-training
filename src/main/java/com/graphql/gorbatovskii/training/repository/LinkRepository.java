package com.graphql.gorbatovskii.training.repository;

import com.graphql.gorbatovskii.training.model.Link;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_SINGLETON;

@Component
@Scope(SCOPE_SINGLETON)
public class LinkRepository {

    private final List<Link> links;

    public LinkRepository() {
        this.links = Collections.synchronizedList(new ArrayList<>(
            Arrays.asList(
                new Link("http://howtographql.com", "Your favorite GraphQL page"),
                new Link("http://graphql.org/learn/", "The official docks")
            )
        ));
    }

    public List<Link> getAllLinks() {
        return this.links;
    }

    public void saveLink(Link link) {
        this.links.add(link);
    }
}
