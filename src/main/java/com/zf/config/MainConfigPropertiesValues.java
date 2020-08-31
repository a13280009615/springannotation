package com.zf.config;

import com.zf.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author zhengfan
 * @create 2020-08-31 23:11
 */
@Configuration
// 使用  读取外部配置文件的 k/v 保存到运行的环境变量中
@PropertySource({"classpath:/person.properties"})
public class MainConfigPropertiesValues {


    @Bean
    public Person person(){
        return  new Person();
    }
}
