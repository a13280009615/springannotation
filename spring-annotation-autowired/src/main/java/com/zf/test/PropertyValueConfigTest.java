package com.zf.test;

import com.zf.bean.Person;
import com.zf.config.PropertyValueConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author zhengfan
 * @create 2020-09-26 22:17
 */
public class PropertyValueConfigTest {

    @Test
    public  void  test(){
        AnnotationConfigApplicationContext ioc = new AnnotationConfigApplicationContext(PropertyValueConfig.class);

        Person person = (Person) ioc.getBean("person");
        System.out.println(person);
        ConfigurableEnvironment environment = ioc.getEnvironment();
        String property = environment.getProperty("person.nickName");
        System.out.println(property);
    }
}
