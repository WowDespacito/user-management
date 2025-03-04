package com.wowdespacito.user_management.exception;

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

    /*
     * 非业务报错，不返回错误具体信息
     */
    @ExceptionHandler(Exception.class)
    public Response<String> handleException(Exception e){
        System.out.println(
            "Exception Class:"+e.getClass().getName()+"\n"+
            "Exception Message:"+e.getMessage()
            );
        e.printStackTrace();
        return Response.error("系统错误");
    }
}
