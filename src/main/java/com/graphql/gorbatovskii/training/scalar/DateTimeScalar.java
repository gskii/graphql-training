package com.graphql.gorbatovskii.training.scalar;

import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.GraphQLScalarType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface DateTimeScalar {

    GraphQLScalarType TYPE = GraphQLScalarType.newScalar()
        .name("DateTime")
        .description("DataTime scalar")
        .coercing(new Coercing() {
            @Override
            public String serialize(Object input) {
                //serialize the ZonedDateTime into string on the way out
                return ((LocalDateTime) input).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            }

            @Override
            public Object parseValue(Object input) {
                return serialize(input);
            }

            @Override
            public LocalDateTime parseLiteral(Object input) {
                //parse the string values coming in
                return LocalDateTime.parse(((StringValue) input).getValue());
            }
        })
        .build();
}
