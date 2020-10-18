package com.zf.ioc.test;

import com.zf.ioc.config.IOCConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author 郑凡
 * @create 2020-09-29 12:25
 */
public class IOCTest {

    @Test
    public  void  test(){
        AnnotationConfigApplicationContext ioc =  new AnnotationConfigApplicationContext(IOCConfig.class);

    }
}
