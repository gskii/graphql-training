package com.graphql.gorbatovskii.training.controller;

import com.graphql.gorbatovskii.training.model.AuthData;
import com.graphql.gorbatovskii.training.model.User;
import com.graphql.gorbatovskii.training.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

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
}
