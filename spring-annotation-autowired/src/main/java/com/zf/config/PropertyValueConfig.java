package com.zf.config;

import com.zf.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author zhengfan
 * @create 2020-09-26 22:15
 * @PropertySource 读取外部配置文件中的 k/v保存到运行的环境变量中
 * 就可以 使用${k}  取值
 */
@Configuration
@PropertySource(value = {"classpath:/person.properties"})
public class PropertyValueConfig {

    @Bean
    public Person person(){
        return  new Person();
    }
}
