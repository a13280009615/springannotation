package com.zf.test;

import com.zf.config.MainConfigLifeCycle;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author zhengfan
 * @create 2020-09-26 21:08
 */
public class MainConfigLifeCycleTest {

    @Test
    public void  test(){
        AnnotationConfigApplicationContext ioc  = new AnnotationConfigApplicationContext(MainConfigLifeCycle.class);
        System.out.println("容器创建完成");
        ioc.close();
    }

}
