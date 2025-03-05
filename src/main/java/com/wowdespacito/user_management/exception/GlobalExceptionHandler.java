package com.wowdespacito.user_management.exception;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import com.wowdespacito.user_management.pojo.Response;


@RestControllerAdvice
public class GlobalExceptionHandler {
    /*
     * 自定义的业务报错
     */
    @ExceptionHandler(MyException.class)
    public Response<String> handleMyException(MyException e) {
        System.out.println(
            "Exception Class:"+e.getClass().getName()+"\n"+
            "Exception Message:"+e.getMessage()
            );
        return Response.error(e.getMessage());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Response<String> handleMissingParams(MissingServletRequestParameterException ex) {
        String name = ex.getParameterName();
        return Response.error(name + " parameter is missing");
    }

    /*
     * 非业务报错，未知的异常返回系统错误
     */
    @ExceptionHandler(Exception.class)
    public Response<String> handleException(Exception e){
        System.out.println(
            "Exception Class:"+e.getClass().getName()+"\n"+
            "Exception Message:"+e.getMessage()
            );
        e.printStackTrace();
        return Response.error(StringUtils.hasLength(e.getMessage())? e.getMessage():"系统错误");
    }
}
