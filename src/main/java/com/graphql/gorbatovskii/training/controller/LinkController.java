package com.graphql.gorbatovskii.training.controller;

import com.graphql.gorbatovskii.training.model.Link;
import com.graphql.gorbatovskii.training.model.User;
import com.graphql.gorbatovskii.training.repository.LinkRepository;
import com.graphql.gorbatovskii.training.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.ContextValue;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class LinkController {

    private final LinkRepository linkRepository;
    private final UserRepository userRepository;

    @Autowired
    public LinkController(final LinkRepository linkRepository,
                          final UserRepository userRepository) {
        this.linkRepository = linkRepository;
        this.userRepository = userRepository;
    }

    @QueryMapping
    public List<Link> allLinks() {
        return this.linkRepository.findAll();
    }

    @QueryMapping
    public Link linkById(final @Argument String id) {
        return this.linkRepository.findById(id).orElse(null);
    }

    @MutationMapping
    public Link createLink(final @Argument String url,
                           final @Argument String description,
                           final @ContextValue(required = false) User user) {
        return this.linkRepository.save(
            new Link(url, description, user == null ? null : user.getId())
        );
    }

    @SchemaMapping
    public User postedBy(Link link) {
        return Optional.ofNullable(
            link.getUserId()
        ).flatMap(
            this.userRepository::findById
        ).orElse(null);
    }
}
