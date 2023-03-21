package com.graphql.gorbatovskii.training.repository;

import com.graphql.gorbatovskii.training.model.Link;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LinkRepository extends MongoRepository<Link, String> {

}
