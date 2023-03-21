package com.graphql.gorbatovskii.training.repository;

import com.graphql.gorbatovskii.training.model.Vote;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VoteRepository extends MongoRepository<Vote, String> {

}
