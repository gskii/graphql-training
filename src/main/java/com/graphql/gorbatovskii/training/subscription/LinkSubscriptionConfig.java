package com.graphql.gorbatovskii.training.subscription;

import com.graphql.gorbatovskii.training.model.Link;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.Many;
import reactor.util.concurrent.Queues;

@Configuration
public class LinkSubscriptionConfig {

    @Bean
    public Many<Link> linkSink() {
        return Sinks.many().multicast().onBackpressureBuffer(Queues.SMALL_BUFFER_SIZE, false);
    }

    @Bean
    public Flux<Link> linkFlux(Many<Link> linkSink) {
        return linkSink.asFlux();
    }
}
