package com.zf.config;


import com.zf.bean.Color;
import com.zf.bean.ColorFactory;
import com.zf.bean.Person;
import com.zf.condition.LinuxCondition;
import com.zf.condition.WindowsCondition;
import com.zf.improt.MyImportBeanDefinitionRegistrar;
import com.zf.improt.MyImportSelector;
import org.springframework.context.annotation.*;

//告诉 spring  这是一个配置类
@Configuration
/**组件扫描 扫描 哪些包  在ioc容器中 默认的过滤器 是 扫描所有
如果需要 按需加载  需要关闭 默认的ioc 容器过滤条件
 */

/**
 * @ComponentScan.Filter过滤的5种方式
 *     FilterType.Annotation  按照注解的方式
 *     FilterType.ASSIGNABLE_TYPE  按照类型  @ComponentScan.Filter(type = FilterType.CUSTOM,classes = {BookService.class})
 *     FilterType.ASPECTJ     不常用
 *     FilterType.REGEX       正则表达式
 *     FilterType.CUSTOM      自定义规则
 *     必须要实现 TypeFilter接口
 */
@ComponentScan(value = "com.zf",includeFilters = {
      //  @ComponentScan.Filter(type = FilterType.CUSTOM,classes = {MyTypeFilter.class})
},useDefaultFilters = false)

/**
 * *
 *  *
 *  *
 *  * 给容器中组件的方式：
 *  *    1 包扫描 + 组件标注  注解 @controller @service @repository @compent
 *  *    2 @Bean 导入第三方的包里面的组件
 *  *    3 @Import 快速的给容器中导入组件  id 默认是 com.zf.bean.Color 全限定类名 可以导入多个
 *          3.1 @Import({Color.class,.....})  直接导入
 *          3.2 ImportSelector 接口
 *          3.3 ImportBeanDefinitionRegistrar
 *       4 Spring 提供的FactoryBean(工厂bean)
 */
@Import({Color.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
public class MainConfig {


    /**
     * @bean 给容器中 注册一个bean 类型为返回值类型 id默认为方法名  @bean("perosn") 可以指定id名
     * @return  返回值就是给容器注册的这个bean的实例
     * @Scope  定义 该实例是否是 多例  还是 单例   prototype  singleton request  session  默认为单例的ioc
     * 容器启动 会调用该方法 并且将创建对象放在ioc容器中  如果是多实例情况下 在获取的时候就会被调用方法创建
     * 该对象 每次调用 都会创建一个对象
     * 在单例的情况下  容器启动的时候就创建了该实例 如果想先不创建该对象 可以使用 @Lazy
     *
     */
    @Scope(value = "prototype")
    @Bean
    public Person person(){
        return  new Person(1,"zhangsan");
    }


    /**
     * @Conditional  条件渲染  需要实现 condition接口 重写方法
     * 可以标注在类上  如果条件成功 那么整个类将会被加载
     * 可以标注在方法上  判断某一个组件的条件是否满足 满足则创建 不满足不创建
     * @return
     */

    @Conditional(WindowsCondition.class)
    @Bean("bill")
    public Person person01(){
        return  new Person(2,"bill Gates");
    }
    @Conditional(LinuxCondition.class)
    @Bean("linus")
    public Person person02(){
        return  new Person(3,"linus");
    }



/**  注册的 factoryBean  其实就是 factoryBean 的 getObeject()方法 如果想在ioc容器中
    拿到这个 factoryBean  需要在id前面 加一个&
 */
    @Bean
    public ColorFactory colorFactory(){
        return  new ColorFactory();
    }

}
