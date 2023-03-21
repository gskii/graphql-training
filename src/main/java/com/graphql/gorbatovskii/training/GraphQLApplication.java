package com.graphql.gorbatovskii.training;

import com.graphql.gorbatovskii.training.scalar.DateTimeScalar;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@SpringBootApplication
public class GraphQLApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraphQLApplication.class, args);
    }

    @Bean
    public RuntimeWiringConfigurer configurer() {
        return (builder) -> builder.scalar(DateTimeScalar.TYPE);
    }
}
