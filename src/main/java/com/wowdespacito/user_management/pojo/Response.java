package com.wowdespacito.user_management.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通用响应类，用于封装API响应数据。
 *
 * @param <T> 响应数据的类型
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Response <T> {
    /**
     * 响应代码
     */
    private String code;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 响应数据
     */
    private T data;

    public static <E> Response<E> success(E data) {
        return new Response<>("SSSS", "success", data);
    }

    public static <E> Response<E> success(){
        return new Response<>("SSSS", "success", null);
    }

    public static <E> Response<E> error(String message) {
        return new Response<>("FFFF", message, null);
    }
}
