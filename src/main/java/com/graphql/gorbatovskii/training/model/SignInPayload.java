package com.graphql.gorbatovskii.training.model;

public class SignInPayload {

    private String token;
    private User user;

    public SignInPayload() {
    }

    public SignInPayload(String token, User user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "SigningPayload{" +
            "token='" + token + '\'' +
            ", user=" + user +
            '}';
    }
}
