package com.zf.bean;

import org.springframework.beans.factory.annotation.Value;

public class Person {
    @Value("#{20 - 2}")
    private Integer id;
    /**
     * @Value赋值
     * 1基本数值
     * 2 SpEL 表达式 #{ }
     * 3 ${} 配置文件的值 在运行环境变量的
     *
     * */
    @Value("张三")
    private String name;
    @Value("${person.nickName}")
    private  String nickName;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Person() {
    }

    public Person(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
