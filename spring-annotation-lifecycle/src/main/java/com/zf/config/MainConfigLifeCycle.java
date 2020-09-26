package com.zf.config;

import com.zf.bean.Car;
import com.zf.bean.Cat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author zhengfan
 * @create 2020-09-26 21:04
 * bean的生命周期
 * bean创建 --初始化 --销毁的过程
 * 我们可以自定义 初始化和销毁的方法 容器在bean进行到当前生命周期的时候来调用我们的自定义的初始化和销毁
 *
 *  对象创建
 *    单实例的 在容器启动的时候创建对象
 *    多实例   在每次获取的时候创建对象
 *
 *  以前的时候
 *    在配置文件中指定 init-methods  和 destroy-methods 方法
 *
 *  使用注解
 *
 *  第一种方式
 *   Car 组件使用
 *  使用@Bean注解指定initMethod和destroyMethod
 *
 *  构造(对象创建)
 *    单实例: 在容器启动的时候创建对象
 *    多实例: 在每次获取的时候创建对象
 *  初始化：
 *    对象创建完成并赋值好 调用初始化方法
 *   销毁：
 *    单实例:容器关闭的时候
 *    多实例:容器不会管理这个bean 容器就不会调用调用方法
 *
 *   第二种方式
 *   cat组件使用
 *    实现 InitializingBean,DisposableBean 接口 定义初始化和销毁方法
 *
 *   第三种方式
 *    Dog 组件使用
 *     可以使用JSR250规范 @PostConstruct 注解 在bean创建完成并且属性赋值之后来执行初始化方法
 *                      @Predestroy  注解  在容器销毁bean之前 通知我们进行清理工作
 *   第四种方式
 *   BeanPostProcessor  bean的后置处理器
 *    在bean初始化前后进行一些处理
 *     postProcessBeforeInitialization 在初始化之前工作
 *     postProcessAfterInitialization  在初始化之后工作
 *
 * BeanPostProcessor原理
 * populateBean(beanName, mbd, instanceWrapper); 给属性赋值
 * initializeBean{
 *    applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName);
 *   invokeInitMethods(beanName, wrappedBean, mbd); 执行自定义初始化方法
 *   applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
 * }
 *
 * Spring底层对BeanPostProcessor的使用
 *  bean赋值  注入其他组件 @Autowired 生命周期 @Async  XXXBeanPostProcessor
 */
@Import({Cat.class})
@Configuration
@ComponentScan("com.zf")
public class MainConfigLifeCycle {

    @Bean(initMethod = "init" ,destroyMethod = "destroy")
    public Car  car(){
        return  new Car();
    }
}
