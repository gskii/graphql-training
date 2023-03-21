package com.graphql.gorbatovskii.training.interceptor;

import com.graphql.gorbatovskii.training.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.server.WebGraphQlInterceptor;
import org.springframework.graphql.server.WebGraphQlRequest;
import org.springframework.graphql.server.WebGraphQlResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.Optional;

@Component
class RequestHeaderInterceptor implements WebGraphQlInterceptor {

    private final UserRepository userRepository;

    @Autowired
    public RequestHeaderInterceptor(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Mono<WebGraphQlResponse> intercept(WebGraphQlRequest request, Chain chain) {
        Optional.ofNullable(
            request.getHeaders().getFirst("Authorization")
        ).map(
            headerValue -> headerValue.replace("Bearer ", "")
        ).flatMap(
            this.userRepository::findById
        ).ifPresent(user ->
            request.configureExecutionInput((executionInput, builder) ->
                builder.graphQLContext(
                    Collections.singletonMap("user", user)
                ).build()
            )
        );
        return chain.next(request);
    }
}

