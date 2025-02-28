package com.wowdespacito.user_management.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.wowdespacito.user_management.pojo.Response;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Response<String> handleException(Exception e) {;
        return Response.error(e.getMessage());
    }

}
