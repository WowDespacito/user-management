package com.wowdespacito.user_management.consumer;

import org.springframework.stereotype.Component;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import com.wowdespacito.user_management.config.RabbbitMQConfig;
import com.wowdespacito.user_management.exception.MyException;
import com.wowdespacito.user_management.service.UserService;

@Component
public class TokenValidationConsumer {
    @Autowired
    private UserService userService;

    @RabbitListener(queues = RabbbitMQConfig.TOKEN_VALIDATION_QUEUE)
    public boolean handleTokenValidation(String token) throws MyException{
        System.out.println("Received token for validation"+token);
        boolean isValid = userService.verifyToken(token);
        return isValid;
    }
}
