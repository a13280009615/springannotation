package com.zf.bean;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author zhengfan
 * @create 2020-09-26 22:15
 */
public class Person {

    /**
     *使用@Value 赋值
     *   基本数值
     *   可以写SpEl #{}
     *   可以写${} 取出配置文件的值(在运行环境的值) 需要在 配置类中 使用@PropertySource 加载外部配置文件
     *
     */

    @Value("李四")
    private  String name;
    @Value("#{20-2}")
    private  Integer age;

    @Value("${person.nickName}")
    private String nickName;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Person() {
    }

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
