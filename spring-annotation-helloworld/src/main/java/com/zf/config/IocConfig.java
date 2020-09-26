package com.zf.config;

import com.zf.bean.Person;
import com.zf.service.BookService;
import com.zf.typefilter.MyTypeFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

/**
 * @author zhengfan
 * @create 2020-09-26 下午5:07
 *
 * @Configuration  告诉 spring 这是一个 配置文件 等于配置文件
 * @ComponentScan  告诉spring  扫描的包是那个
 * value 指定要扫描的包
 * excludeFilters 指定扫描的时候按照什么规则排除那些组件
 * includeFilters 指定扫描的时候按照什么规则包含那些组件 因为 spring 默认的扫描规则是所有组件都包含
 * 要想指定 需要使用includeFilters 需要将useDefaultFilters=false关闭到spring 默认的扫描规则
 *  通过@ComponentScan.Filter 注解 指定规则
 *      @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class}),
 *      @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,classes = {BookService.class}),
 *  FilterType 一共有5种
 *   ANNOTATION
 *   ASSIGNABLE_TYPE 按照什么类型
 *   ASPECTJ
 *   REGEX
 *   CUSTOM  自定义规则
 */
@ComponentScan(value = "com.zf",includeFilters = {
        @ComponentScan.Filter(type = FilterType.CUSTOM,classes = {MyTypeFilter.class})
},useDefaultFilters = false)
@Configuration
public class IocConfig {


    @Bean
    public Person person(){
        return   new Person("李四",20);
    }
}
