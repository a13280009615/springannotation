package com.zf.test;

import com.zf.aop.MathCalculator;
import com.zf.config.AopConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author zhengfan
 * @create 2020-09-27 1:07
 */
public class AopConfigTest {

    @Test
    public  void  test(){
        AnnotationConfigApplicationContext ioc = new AnnotationConfigApplicationContext(AopConfig.class);
        MathCalculator calculator = ioc.getBean(MathCalculator.class);
        calculator.div(2, 2);

    }
}
