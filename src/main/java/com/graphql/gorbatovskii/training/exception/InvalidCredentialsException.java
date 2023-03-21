package com.graphql.gorbatovskii.training.exception;

import graphql.GraphQLException;

public class InvalidCredentialsException extends GraphQLException {

    public InvalidCredentialsException() {
    }

    public InvalidCredentialsException(String message) {
        super(message);
    }

    public InvalidCredentialsException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidCredentialsException(Throwable cause) {
        super(cause);
    }
}
