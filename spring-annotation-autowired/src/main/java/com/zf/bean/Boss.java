package com.zf.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zhengfan
 * @create 2020-09-26 23:17
 */
@Component
public class Boss {

    private  Car car;

    @Autowired
    public Boss(Car car) {
        this.car = car;
    }

    public Car getCar() {
        return car;
    }

    /**
     * Autowired 标注在方法上 spring容器创建当前对象的时候就会调用方法完成赋值
     * 方法使用的自定义类型的参数 从ioc 容器中获取
     * @param car
     */
  //  @Autowired
    public void setCar(Car car) {
        this.car = car;
    }
}
