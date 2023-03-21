package com.graphql.gorbatovskii.training.repository;

import com.graphql.gorbatovskii.training.model.Link;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface LinkRepository extends MongoRepository<Link, String> {

    List<Link> findByUrlContainsAndDescriptionContains(String urlContains, String descriptionContains);
}
