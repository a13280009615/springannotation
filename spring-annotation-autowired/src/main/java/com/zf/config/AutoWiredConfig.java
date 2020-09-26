package com.zf.config;

import com.zf.bean.Car;
import com.zf.bean.Color;
import com.zf.dao.BookDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author zhengfan
 * @create 2020-09-26 22:32
 *
 *  1自动装配
 *    spring 利用DI 完成对IOC容器中各个组件的依赖关系赋值
 *
 *    @Autowired 自动装配
 *    1 默认优先按照类型去容器中的组件如果找到就赋值
 *    2 如果找到多个相同的组件 再将属性名作为id去容器中查找
 *    3 @Qualifier 指定需要那个装配那个组件的id 而不是使用属性名
 *    4 自动装配一定要将属性赋值好 如果没有就会报错 NoSuchBeanDefinitionException 可以使用
 *     @Autowired(required = false)
 *    5 @Primary 注解 让spring 进行自动装配的时候 默认使用首选的bean
 *     也可以继续使用@Qualifier 到底使用那个
 * 2 spring 还支持 JSR250规范的 @Resource  和JSR330规范的  @Inject  java规范的注解
 *    @Resource : java规范 和@Autowired 一样可以实现自动装配功能  默认是按照组件的名称进行装配的
 *    也可以使用@Resource(name = "bookDao2")  还用name 指定要装配的组件 没有支持@Primary
 *    和支持 @@Autowired(required = false) 的功能
 *
 * @Inject 的使用 需要导入一个依赖 javax.inject依赖 没有required属性  他也支持@Primary
 *
 * 3 @Autowired 是 spring定义的 @Resource 和@Inject都是java规范
 *  且 可以标注在 构造器 参数 方法 属性上  都是从容器中获取参数的值
 *   标在构造器上 如果组件只有一个有参构造器 这个有参构造器的@Autowired 可以省略 spring也会给这个组件
 *   赋值
 *   在使用@Bean 给容器中添加组件的时候 如果参数是自定义类型 默认也是从容器中获取
 *
 * AutowiredAnnotationBeanPostProcessor 解析完成自动装配功能的
 *
 *  4自定义组件想要使用Spring 容器底层的一些组件 只需要让自定义组件实现 XXXAware接口
 *  在创建对象的时候 会调用接口规定的方法注入相关的组件
 *  没一个 XXXAware都有 一个 XXXAwareProcessor
 */
@Configuration
@ComponentScan("com.zf")
public class AutoWiredConfig {
    @Primary
   @Bean("bookDao2")
    public BookDao bookDao(){
        BookDao bookDao = new BookDao();
        bookDao.setLable("2");
        return bookDao;
    }


    /**
     * @Bean 标注的方法创建对象的时候
     * @param car 也是从容器中获取
     * @return
     */

    @Bean
    public Color color(Car car){
        Color color = new Color();
        color.setCar(car);
        return color;
    }

}
