package com.zf.service;

import org.springframework.stereotype.Service;

/**
 * @author 郑凡
 * @create 2020-09-29 16:37
 */
@Service
public class HelloService {

    public String sayHello(String name){
        return "Hello" + name;
    }
}
