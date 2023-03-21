package com.graphql.gorbatovskii.training.controller;

import com.graphql.gorbatovskii.training.model.AuthData;
import com.graphql.gorbatovskii.training.model.Link;
import com.graphql.gorbatovskii.training.model.SignInPayload;
import com.graphql.gorbatovskii.training.model.User;
import com.graphql.gorbatovskii.training.repository.UserRepository;
import graphql.GraphQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @MutationMapping
    public User createUser(final @Argument String name,
                           final @Argument AuthData authProvider) {
        return this.userRepository.save(
            new User(name, authProvider.getEmail(), authProvider.getPassword())
        );
    }

    @MutationMapping
    public SignInPayload signInUser(final @Argument AuthData auth) {
        final User user = this.userRepository.findByEmail(auth.getEmail());
        if (user.getPassword().equals(auth.getPassword())) {
            return new SignInPayload(user.getId(), user);
        }
        throw new GraphQLException("Invalid credentials");
    }

    @SchemaMapping
    public User user(final SignInPayload payload) {
        return payload.getUser();
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
