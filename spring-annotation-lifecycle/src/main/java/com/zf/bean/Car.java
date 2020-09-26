package com.zf.bean;

/**
 * @author zhengfan
 * @create 2020-09-26 21:06
 */
public class Car {

    public  Car (){
        System.out.println("car  构造器...");
    }

    public  void init(){
        System.out.println("car  初始化...");
    }

    public void destroy(){
        System.out.println("car  销毁中...");
    }
}
