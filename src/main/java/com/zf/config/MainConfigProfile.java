package com.zf.config;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zf.bean.Yellow;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringValueResolver;

import javax.sql.DataSource;

/**
 * profile
 *   Spring 为我们提供了可以根据当前环境 动态的激活和切换一系列组件的功能
 *
 * @profile 指定组件在哪个环境的情况下 才能被注册到容器中
 *
 *  加了环境标识的bean 只有这个环境被激活的时候才会注册到容器中
 *    默认是default 环境
 *
 *    1 . 使用运行的命令指定环境 -Dspring.profiles.active=test
 *    2 .  使用代码的方式
 *
 *        //   创建容器对象
 *         AnnotationConfigApplicationContext ioc = new AnnotationConfigApplicationContext();
 *         //  设置需要激活的环境
 *         ioc.getEnvironment().setActiveProfiles("test","dev");
 *         //注册主配置类
 *         ioc.register(MainConfigProfile.class);
 *         //        启动刷新容器
 *         ioc.refresh();
 *
 * @profile 可以标注在类上  这个环境下 整个类都会被加载  和 condition 类似
 *
 *   没有标识注解环境标识下的bean 在任何环境下都是加载的
 *
 */
@PropertySource({"classpath:/dbconfig.properties"})
@Configuration
public class MainConfigProfile  implements EmbeddedValueResolverAware {

    @Value("${db.user}")
    private  String user;

    private   StringValueResolver  valueResolver;

    private String  driverClass;


    @Profile("test")
    @Bean
    public Yellow yellow(){
        return  new Yellow();
    }

    @Profile("test")
    @Bean("testDataSource")
    public DataSource dataSource(@Value("${db.password}") String pwd) throws Exception {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setUser(user);
        comboPooledDataSource.setPassword(pwd);
        comboPooledDataSource.setJdbcUrl("jdbc:mysql:///test");
        comboPooledDataSource.setDriverClass(driverClass);
        return comboPooledDataSource;
    }

    @Profile("dev")
    @Bean("devDataSource")
    public DataSource dataSourceDev(@Value("${db.password}") String pwd) throws Exception {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setUser(user);
        comboPooledDataSource.setPassword(pwd);
        comboPooledDataSource.setJdbcUrl("jdbc:mysql:///test");
        comboPooledDataSource.setDriverClass(driverClass);
        return comboPooledDataSource;
    }
    @Profile("pro")
    @Bean("proDataSource")
    public DataSource dataSourcePro(@Value("${db.password}") String pwd) throws Exception {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setUser(user);
        comboPooledDataSource.setPassword(pwd);
        comboPooledDataSource.setJdbcUrl("jdbc:mysql:///test");

        comboPooledDataSource.setDriverClass(driverClass);
        return comboPooledDataSource;
    }

    public void setEmbeddedValueResolver(StringValueResolver resolver) {
           this.valueResolver = resolver;
           driverClass = valueResolver.resolveStringValue("${db.driverClass}");
    }
}
