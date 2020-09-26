package com.zf.config;

import com.zf.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

/**
 * @author zhengfan
 * @create 2020-09-26 下午7:35
 */
@Configuration
public class MainConfig {

    /**
     * @Bean 注解  和  xml中 bean标签是一样的 给容器中注册一个bean
     *类型就是 返回值类型  id默认是用法名作为id  如果想修改在ioc容器中的id
     * @Bean("指定的id名称")
     *
     * @Scope 调整bean的作用域
     *  value的 取值范围
     *  SCOPE_PROTOTYPE   多实例
     * 	SCOPE_SINGLETON   单实例 默认值
     * 	#SCOPE_REQUEST    同一次请求创建一个实例
     * 	#SCOPE_SESSION    同一个session创建一个实例
     *
     * 	ioc容器创建的时候 单实例的bean 会被立即创建 如果想要在获取的时候才创建对象
     * 	               多实例的bean 不会被立即创建 每次获取都会创建一个新的对象
     *
     * @Lazy 注解  针对于单实例的bean  因为 容器启动的时候立即创建所有单实例的bean
     * 该注解就是让 容器启动的时候不创建对象 在获取的时候才会创建
     */
    @Lazy
    @Bean
    public Person person(){
        return  new Person("张三",18);
    }
}
