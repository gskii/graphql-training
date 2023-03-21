package com.graphql.gorbatovskii.training.repository;

import com.graphql.gorbatovskii.training.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    User findByEmail(String email);
}
