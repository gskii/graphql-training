package com.graphql.gorbatovskii.training.controller;

import com.graphql.gorbatovskii.training.exception.InvalidCredentialsException;
import com.graphql.gorbatovskii.training.model.AuthData;
import com.graphql.gorbatovskii.training.model.SignInPayload;
import com.graphql.gorbatovskii.training.model.User;
import com.graphql.gorbatovskii.training.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
public class SignInController {

    private final UserRepository userRepository;

    @Autowired
    public SignInController(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @MutationMapping
    public SignInPayload signInUser(final @Argument AuthData auth) {
        final User user = this.userRepository.findByEmail(auth.getEmail());
        if (user.getPassword().equals(auth.getPassword())) {
            return new SignInPayload(user.getId(), user);
        }
        throw new InvalidCredentialsException("Invalid credentials");
    }

    @SchemaMapping
    public User user(final SignInPayload payload) {
        return payload.getUser();
    }
}
