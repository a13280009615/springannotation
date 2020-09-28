package com.zf.test;

import com.zf.config.ExtensionConfig;
import org.junit.Test;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author zhengfan
 * @create 2020-09-28 23:38
 */
public class ExtTest {

    @Test
    public void  test(){
        AnnotationConfigApplicationContext ioc = new AnnotationConfigApplicationContext(ExtensionConfig.class);
        //发布事件 使用publishEvent()
        ioc.publishEvent(new ApplicationEvent(new String("我发布的事件")) {

        });
        ioc.close();
    }
}
