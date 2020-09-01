package com.zf.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//默认加载ioc容器中组件 容器启动会调用无参构造器  在进行初始化赋值操作
@Component
public class Boss {

    private  Car car;

//    标注在构造器上  参数也会从 ioc 拿
//    @Autowired
    public Boss(@Autowired Car car){
        this.car = car;
    }

    public Car getCar() {
        return car;
    }

    //标注在方法上 Spring容器创建对象 就会调用方法 完成赋值
//    方法使用的参数 自定义类型的值 从ioc容器中获取

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Boss{" +
                "car=" + car +
                '}';
    }
}
