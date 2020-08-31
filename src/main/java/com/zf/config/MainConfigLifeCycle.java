package com.zf.config;

import com.zf.bean.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * bean的声明周期：
 *  bean创建  --- 初始化   ---销毁的过程
 *
 *  我们可以自定义初始化和销毁的方法 容器在bean 进行到当前声明周期的时候 调用我们自定义的初始化和销毁
 *  方法
 *
 *  构造器:
 *     单实例: 容器启动的时候创建对象
 *     多实例: 在每次获取的时候创建对象
 *
 *   初始化:
 *          对象创建完成 并赋值好 调用初始化方法
 *    销毁:
 *      单实例:    容器关闭的时候
 *      多实例:    容器不会管理这个bean 容器不会调用销毁方法
 *
 *  1  @bean 注解  init-method   destroy-method
 *
 *  2  通过让bean 实现InitializingBean  DisposableBean 接口
 *
 *  3 JSR250规范 PostConstruct（bean创建完成 属性赋值完成）
 *              ProDestroy（在容器销毁bean之前通知我们进行清理工作）
 *
 *  4  BeanPostProcessor ： bean的后置处理器
 *         postProcessBeforeInitialization 组件init之前
 *         postProcessAfterInitialization  组件init之后
 */
@Configuration
@ComponentScan("com.zf.bean")
public class MainConfigLifeCycle {

    @Bean(initMethod = "init",destroyMethod = "Destroy")
    public Car car(){
        return  new Car();
    }
}
