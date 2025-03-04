package com.wowdespacito.user_management.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbbitMQConfig {
    // RabbitMQ configuration
    public static final String TOKEN_VALIDATION_QUEUE = "tokenValidationQueue";
    public static final String TOKEN_VALIDATION_REPLY_QUEUE = "tokenValidationReplyQueue";

    @Bean
    public Queue tokenValidationQueue() {
        return new Queue(TOKEN_VALIDATION_QUEUE);
    }

    @Bean
    public Queue tokenValidationReplyQueue(){
        return new Queue(TOKEN_VALIDATION_REPLY_QUEUE);
    }
}
