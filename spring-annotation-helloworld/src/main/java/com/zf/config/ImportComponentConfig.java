package com.zf.config;

import com.zf.bean.Color;
import com.zf.bean.ColorFactoryBean;
import com.zf.bean.Red;
import com.zf.imports.MyImportBeanDefinitionRegistrar;
import com.zf.imports.MyImportSelector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author zhengfan
 * @create 2020-09-26 下午8:14
 * 导入第三方的包的组件
 *  使用@Bean 导入
 *  使用@import
 *    1@Import({Color.class, Red.class})快速给容器中倒入组件 id默认是组件的全类名 直接写要倒入的组件容器中就会自动注册这个组件
 *    2ImportSelector: 导入选择器 返回需要倒入的组件的全类名 是一个接口 MyImportSelector
 *    3ImportBeanDefinitionRegistrar 也是一个接口
 *  使用Spring提供的factoryBeanC
 */
@Configuration
@Import({Color.class, Red.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
public class ImportComponentConfig {


    /**
     *  使用Spring提供的 factoryBean 注册 需要将factoryBean 注册到容器中
     * @return
     */
    @Bean
    public ColorFactoryBean colorFactoryBean(){
        return  new ColorFactoryBean();
    }

}
