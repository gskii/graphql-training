package com.graphql.gorbatovskii.training.repository;

import com.graphql.gorbatovskii.training.GraphQLApplicationContextProvider;
import com.graphql.gorbatovskii.training.model.Link;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface LinkRepository extends MongoRepository<Link, String> {

    default List<Link> findAllByUrlContainsAndDescriptionContains(final String urlContains,
                                                                  final String descriptionContains,
                                                                  final int skip,
                                                                  final int first) {
        final MongoTemplate template = GraphQLApplicationContextProvider
            .getApplicationContext()
            .getBean(MongoTemplate.class);
        return template.find(
            new Query()
                .addCriteria(Criteria
                    .where("url").regex(".*" + urlContains + ".*"))
                .addCriteria(Criteria
                    .where("description").regex(".*" + descriptionContains + ".*"))
                .skip(skip)
                .limit(first)
            , Link.class
        );
    }
}
