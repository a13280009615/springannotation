package com.zf.bean;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author zhengfan
 * @create 2020-09-26 21:30
 */
@Component
public class Dog implements ApplicationContextAware {

    private  ApplicationContext applicationContext;

    public  Dog(){
        System.out.println("dog construct....");
    }

    //对象创建并赋值之后
    @PostConstruct
    public void init(){
        System.out.println("dog init..........");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("dog  destroy........ ");
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
         this.applicationContext = applicationContext;
    }
}
