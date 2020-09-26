package com.zf.config;

import com.zf.aop.LogAspects;
import com.zf.aop.MathCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author zhengfan
 * @create 2020-09-27 0:42
 *
 *  指在程序运行期间将某段代码切入到指定方法指定位置进行运行的编码方式
 *
 *  定义一个业务逻辑类 MathCalculator 在业务逻辑运行的时候 将日志进行打印
 *  定义一个日志切面类 LogAspects 切面类的方法需要动态感知
 *    通知方法
 *      前置通知
 *      后置通知  无论方法正常结束还是异常结束都调用
 *      返回通知
 *      异常通知
 *      环绕通知  joinPoint.procced()
 *
 *   给切面类目标方法标注何时何地运行
 *
 *   将切面类和业务逻辑类(目标方法所在的类) 都加入到 容器中
 *
 *   告诉spring 哪个类是切面类 给切面类加一个注解 @Aspect
 *
 *   开启基于注解的切面功能  @EnableAspectJAutoProxy
 *     在使用JoinPoint 作为参数的 时候 一定要把他放在 第一个参数 不然就会报错
 */
@EnableAspectJAutoProxy
@Configuration
public class AopConfig {

    @Bean
    public MathCalculator mathCalculator(){
        return  new MathCalculator();
    }

    @Bean
    public LogAspects logAspects(){
        return  new LogAspects();
    }
}
