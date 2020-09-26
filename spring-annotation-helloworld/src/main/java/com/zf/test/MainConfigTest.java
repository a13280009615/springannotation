package com.zf.test;

import com.zf.bean.Person;
import com.zf.config.IocConfig;
import com.zf.config.MainConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author zhengfan
 * @create 2020-09-26 下午7:36
 */
public class MainConfigTest {

    private ApplicationContext ioc = new AnnotationConfigApplicationContext(MainConfig.class);


    @Test
    public  void test(){
        Person person = (Person) ioc.getBean("person");
        Person person1 = (Person) ioc.getBean("person");
        System.out.println(person == person1);  //true
    }
}
