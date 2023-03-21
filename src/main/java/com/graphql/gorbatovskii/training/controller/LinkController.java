package com.graphql.gorbatovskii.training.controller;

import com.graphql.gorbatovskii.training.model.Link;
import com.graphql.gorbatovskii.training.repository.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class LinkController {

    private final LinkRepository linkRepository;

    @Autowired
    public LinkController(final LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    @QueryMapping
    public List<Link> allLinks() {
        return this.linkRepository.getAllLinks();
    }

    @MutationMapping
    public Link createLink(final @Argument String url,
                           final @Argument String description) {
        Link newLink = new Link(url, description);
        linkRepository.saveLink(newLink);
        return newLink;
    }
}
