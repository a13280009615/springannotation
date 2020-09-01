package com.zf.bean;

import org.springframework.stereotype.Component;

@Component
public class Car {

   public Car(){
       System.out.println("car constructor");
    }

    public  void init(){
        System.out.println("car init ...");
    }

    public  void Destroy(){
        System.out.println("car Destroy....");
    }
}
