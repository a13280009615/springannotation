package com.zf.test;

import com.zf.bean.Person;
import com.zf.config.IocConfig;
import com.zf.config.MainConditionalConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

/**
 * @author zhengfan
 * @create 2020-09-26 下午7:51
 */
public class MainConditionalConfigTest {
    private ApplicationContext ioc = new AnnotationConfigApplicationContext(MainConditionalConfig.class);

    @Test
    public void  test(){

        Map<String, Person> beans = ioc.getBeansOfType(Person.class);
        System.out.println(beans);
    }
}
