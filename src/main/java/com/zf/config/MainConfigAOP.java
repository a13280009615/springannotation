package com.zf.config;

import com.zf.aop.LogAspects;
import com.zf.aop.MyCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


/**
 *  AOP
 *   指在程序运行期间将某段代码动态切入指定方法位置
 *
 *   将业务逻辑bean  和切面类 加载到容器中
 *
 *   @EnableAspectJAutoProxy   开启基于注解的AOP模式
 */

@EnableAspectJAutoProxy
@Configuration
public class MainConfigAOP {

    @Bean
    public MyCalculator myCalculator(){
        return  new MyCalculator();
    }
    @Bean
    public LogAspects logAspects(){
        return  new LogAspects();
    }
}
