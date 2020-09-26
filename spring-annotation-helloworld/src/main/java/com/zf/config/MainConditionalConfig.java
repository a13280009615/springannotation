package com.zf.config;

import com.zf.bean.Person;
import com.zf.condition.MacCondition;
import com.zf.condition.WindowsCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import javax.crypto.Mac;

/**
 * @author zhengfan
 * @create 2020-09-26 下午7:35
 *
 *  @Conditional
 *  按照条件进行判断 满足条件给容器中注册组件
 *  标注在方法上 只用于当前方法
 *  标注在类上  如果条件满足整个类全部加载
 *
 *  如果系统是linux 系统 就放linus   如果是 windows 就放bill
 */
@Conditional(WindowsCondition.class)
@Configuration
public class MainConditionalConfig {


    @Bean
    public  Person person(){
        return new Person("haha",44);
    }

    @Conditional(WindowsCondition.class)
    @Bean("bill")
    public Person person1(){
        return  new Person("Bill Gates",63);
    }

    @Conditional(MacCondition.class)
    @Bean("steve")
    public Person person2(){
        return  new Person("Steve jobs",55);
    }

}
