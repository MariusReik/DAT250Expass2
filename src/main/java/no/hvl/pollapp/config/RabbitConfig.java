package no.hvl.pollapp.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public FanoutExchange pollExchange() {
        return new FanoutExchange("polls.exchange");
    }

    @Bean
    public Queue pollQueue() {
        return new AnonymousQueue();
    }

    @Bean
    public Binding binding(FanoutExchange pollExchange, Queue pollQueue) {
        return BindingBuilder.bind(pollQueue).to(pollExchange);
    }
}