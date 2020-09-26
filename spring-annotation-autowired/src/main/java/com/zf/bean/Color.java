package com.zf.bean;

/**
 * @author zhengfan
 * @create 2020-09-26 23:29
 */
public class Color {

    private  Car car;

    public Car getCar() {
        return car;
    }

    @Override
    public String toString() {
        return "Color{" +
                "car=" + car +
                '}';
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
