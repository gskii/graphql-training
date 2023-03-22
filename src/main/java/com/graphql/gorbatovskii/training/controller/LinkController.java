package com.graphql.gorbatovskii.training.controller;

import com.graphql.gorbatovskii.training.model.Link;
import com.graphql.gorbatovskii.training.model.LinkFilter;
import com.graphql.gorbatovskii.training.model.User;
import com.graphql.gorbatovskii.training.repository.LinkRepository;
import com.graphql.gorbatovskii.training.repository.UserRepository;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.ContextValue;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks.Many;

import java.util.List;
import java.util.Optional;

@Controller
public class LinkController {

    private final LinkRepository linkRepository;
    private final UserRepository userRepository;
    private final Many<Link> linkSink;
    private final Flux<Link> linkFlux;

    @Autowired
    public LinkController(final LinkRepository linkRepository,
                          final UserRepository userRepository,
                          final Many<Link> linkSink,
                          final Flux<Link> linkFlux) {
        this.linkRepository = linkRepository;
        this.userRepository = userRepository;
        this.linkSink = linkSink;
        this.linkFlux = linkFlux;
    }

    @QueryMapping
    public List<Link> allLinks(final @Argument LinkFilter filter) {
        return this.linkRepository.findByUrlContainsAndDescriptionContains(
            Optional.ofNullable(filter).map(LinkFilter::getUrlContains).orElse(""),
            Optional.ofNullable(filter).map(LinkFilter::getDescriptionContains).orElse("")
        );
    }

    @QueryMapping
    public Link linkById(final @Argument String id) {
        return this.linkRepository.findById(id).orElse(null);
    }

    @MutationMapping
    public Link createLink(final @Argument String url,
                           final @Argument String description,
                           final @ContextValue(required = false) User user) {
        return this.publishNewLink(
            this.linkRepository.save(
                new Link(url, description, user == null ? null : user.getId())
            )
        );
    }

    private Link publishNewLink(Link link) {
        this.linkSink.tryEmitNext(link);
        return link;
    }

    @SchemaMapping
    public User postedBy(Link link) {
        return Optional.ofNullable(
            link.getUserId()
        ).flatMap(
            this.userRepository::findById
        ).orElse(null);
    }

    @SubscriptionMapping
    public Publisher<Link> newLink() {
        return linkFlux;
    }
}
