package com.wowdespacito.user_management.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.wowdespacito.user_management.pojo.User;

@Mapper
public interface UserMapper {
    void updateUser(User user);

    User findUser(User user);
    
    @Insert("INSERT INTO data_user (username, email, password, role, status, create_time, update_time) VALUES (#{username}, #{email}, #{password}, #{role}, #{status}, #{createTime}, #{updateTime})")
    void insertUser(User user);

    @Select("SELECT * FROM data_user")
    List<User> allUsers();
}
