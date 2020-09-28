package com.zf.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author zhengfan
 * @create 2020-09-28 22:43
 */
@Repository
public class UserDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void insert(){
        String sql = "insert into t_user(username,age) values(?,?)";
        String username = UUID.randomUUID().toString().substring(0, 5);
        jdbcTemplate.update(sql,username,20);
    }
}
