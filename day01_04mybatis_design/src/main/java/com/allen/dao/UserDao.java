package com.allen.dao;

import com.allen.domain.User;
import com.allen.mybatis.annotation.Select;

import java.util.List;

public interface UserDao {
    /**
     * 查询所有
     * @return
     */
    @Select("select * from user")
    List<User> findAll();
}
