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
 * @create 2020-08-31 23:33
 *
 * 自动装配：
 *  1 @Autowired(Spring 自己的)   Spring 利用DI 完成对IOC容器中各个组件依赖关系赋值
 *
 *  public class BookService {
 *   1 默认优先按照类型去 ioc容器中查找 如果找到就给他赋值
 *   2 如果找到多个相同的类型 再将属性的名称作为组件的id去容器中查找
 *   3 @Qualifier("bookDao" ) 使用 @Qualifier指定需要装配的组件的id
 *   4 自动装配默认 一定将属性赋值好 如果容器没有注入的这个组件 默认会报错
 *     此时结合@Autowired(required=false)
 *   5 @Primary: 让 ioc 自动装配的时候 默认使用带该注解的组件（背景是ioc 有多个相同类型的组件）
 *     此时和注入的属性 BookDao bookDao无关。 也可以是用@Qualifier 指定需要指定的那个组件
 *     @Qualifier 注解的优先级要 高于@Primary注解
 *
 *     @Autowired
 *     private BookDao bookDao;
 *    }
 *
 *    2 Spring 还支持使用@Resource（JSR250） 和@ Inject（JSR330） [java规范]
 *
 *       @Resource
 *         可以和   @Autowired 一样实现自动装配功能 默认是按照组件名称进行注入
 *        @Resource(name="") 指定组件名称  不支持 @Primary 的功能  不支持
 *        @Autowired(required=false) 的功能
 *
 *
 *    3 Inject  必须要加入 javax.Inject 的jar 包
 *
 *       功能和 @Autowired 一样
 *       不支持   @Autowired(required=false) 的功能
 *
 *     @RESOURCE  @Inject  是 java 规范
 *
 *
 *
 *   @Autowired  可以 构造器 参数 方法 属性;
 *
 *    如果这个组件只有一个有参构造器 这个有参构造器的 @Autowired 可以省略
 *    参数位置的组件可以从ioc容器汇总获取
 *
 *
 *  4 自定义组件想要使用Spring底层的一些组件(ApplicationContext BeanFactory  xxx);
 *   自定义组件实现 。。。。。Aware接口;  把Spring底层一些组件注入到自定义的Bean中
 *
 *
 */
@Configuration
@ComponentScan({"com.zf.controller","com.zf.service","com.zf.dao","com.zf.bean"})
public class MainConfigAutowired {



    @Primary
    @Bean("bookDao2")
    public BookDao bookDao(){
        return  new BookDao("2");
    }


    /**
     *
     * @BEAN标注的方法 创建对象的时候 方法的参数从容器中拿
     * @param car
     * @return
     */

    @Bean
    public Color color(Car car){
        Color color = new Color();
        color.setCar(car);
        return  color;
    }
}
