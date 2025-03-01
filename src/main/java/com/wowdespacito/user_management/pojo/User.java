package com.wowdespacito.user_management.pojo;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

/**
 * 用户实体类，表示系统中的用户信息。
 */
@Data
public class User {
    /**
     * 用户ID
     */
    @NotNull
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户邮箱
     */
    @Email
    @NotNull
    private String email;

    /**
     * 用户密码（忽略序列化）
     */
    @JsonIgnore
    private String password;

    /**
     * 用户角色
     */
    private Integer role = 0;

    /**
     * 用户状态
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
