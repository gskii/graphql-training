package com.graphql.gorbatovskii.training.controller;

import com.graphql.gorbatovskii.training.model.Link;
import com.graphql.gorbatovskii.training.model.User;
import com.graphql.gorbatovskii.training.model.Vote;
import com.graphql.gorbatovskii.training.repository.LinkRepository;
import com.graphql.gorbatovskii.training.repository.UserRepository;
import com.graphql.gorbatovskii.training.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class VoteController {

    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final LinkRepository linkRepository;

    @Autowired
    public VoteController(final VoteRepository voteRepository,
                          final UserRepository userRepository,
                          final LinkRepository linkRepository) {
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
        this.linkRepository = linkRepository;
    }

    @MutationMapping
    public Vote createVote(final @Argument String linkId,
                           final @Argument String userId) {
        final LocalDateTime now = LocalDateTime.now();
        return this.voteRepository.save(
            new Vote(now, userId, linkId)
        );
    }

    @SchemaMapping
    public User user(final Vote vote) {
        return this.userRepository.findById(vote.getUserId()).orElse(null);
    }

    @SchemaMapping
    public Link link(final Vote vote) {
        return this.linkRepository.findById(vote.getLinkId()).orElse(null);
    }
}
