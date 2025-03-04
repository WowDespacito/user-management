package com.wowdespacito.user_management.consumer;

import org.springframework.stereotype.Component;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.wowdespacito.user_management.config.RabbbitMQConfig;
import com.wowdespacito.user_management.exception.MyException;
import com.wowdespacito.user_management.service.UserService;

@Component
public class TokenValidationConsumer {
    @Autowired
    private UserService userService;
    
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = RabbbitMQConfig.TOKEN_VALIDATION_QUEUE)
    public void handleTokenValidation(String token) throws MyException{
        System.out.println("Received token for validation"+token);
        boolean isValid = userService.verifyToken(token);
        
        String responseQueue = "tokenValidationReplyQueue";
        rabbitTemplate.convertAndSend(responseQueue, isValid);
    }
}
