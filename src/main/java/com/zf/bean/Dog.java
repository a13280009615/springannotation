package com.zf.bean;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class Dog {
    public  Dog(){
        System.out.println("dog Constructor");
    }

//    对象创建 并复制之后调用
    @PostConstruct
    public void init(){
        System.out.println("dog init");
    }


    @PreDestroy
    public  void destroy(){
        System.out.println("dog destroy");
    }
}
