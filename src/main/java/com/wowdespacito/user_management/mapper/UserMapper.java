package com.wowdespacito.user_management.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("SELECT id FROM data_user WHERE username = #{username}")
    Integer findUserByUsername(String username);
    
    @Select("SELECT password FROM data_user WHERE id = #{id}")
    String findPasswordById(Integer id);
}
