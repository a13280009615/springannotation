package com.zf.service;

import com.zf.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhengfan
 * @create 2020-09-28 22:42
 */
@Service
public class UserService {
    @Autowired
    UserDao  userDao;

    @Transactional
    public void insertUser(){
        userDao.insert();
        System.out.println("插入完成");
        int t = 1 /0;
    }

}
