package com.wowdespacito.user_management.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbbitMQConfig {
    // RabbitMQ configuration
    public static final String TOKEN_VALIDATION_QUEUE = "tokenValidationQueue";

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean(name=TOKEN_VALIDATION_QUEUE)
    public Queue tokenValidationQueue() {
        return new Queue(TOKEN_VALIDATION_QUEUE);
    }
}
