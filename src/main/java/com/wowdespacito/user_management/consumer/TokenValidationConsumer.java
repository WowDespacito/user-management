package com.wowdespacito.user_management.consumer;

import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import com.wowdespacito.user_management.config.RabbbitMQConfig;
import com.wowdespacito.user_management.exception.MyException;
import com.wowdespacito.user_management.service.UserService;

@Component
public class TokenValidationConsumer {
    @Autowired
    private UserService userService;

    private final static Logger logger = LoggerFactory.getLogger(TokenValidationConsumer.class);

    @RabbitListener(queues = RabbbitMQConfig.TOKEN_VALIDATION_QUEUE)
    public boolean handleTokenValidation(String token) throws MyException{
        logger.info("收到待验证的token："+token);
        boolean isValid = userService.verifyToken(token);
        logger.info("验证结果"+(isValid?"成功":"失败"));
        return isValid;
    }
}
