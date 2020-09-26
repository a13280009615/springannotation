package com.zf.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringValueResolver;

import javax.sql.DataSource;

/**
 * @author zhengfan
 * @create 2020-09-26 23:58
 *
 * profile  spring为我们提供了可以根据当前环境 动态激活和切换一系列组件的功能
 * 例如 开发环境 测试环境  生产环境
 *
 * @profile 指定组件在哪个环境下才能被注册到容器中 不指定任何环境下都能注册这个组件
 *
 *  加了这个环境标识的bean 只有这个环境被激活的情况下才能注册到容器中   默认是 default 环境
 *
 *    使用方式
 *      1 使用命令行参数 在虚拟机参数位置 -Dspring.profiles.active=test 激活环境
 *      2 使用代码的方式激活某种环境
 *         //创建ioc 对象
 *         AnnotationConfigApplicationContext ioc = new AnnotationConfigApplicationContext();
 *         //设置需要激活的环境
 *         ConfigurableEnvironment environment = ioc.getEnvironment();
 *         environment.setActiveProfiles("test","dev");
 *         //注册主配置类
 *         ioc.register(MainConfigProfile.class);
 *         //启动刷新容器
 *         ioc.refresh();
 *       3 没有环境标识的bean 在任何环境下 都会加载
 * 也可以标注在类上  只有在这个环境的时候 整个配置类的所有配置才能生效
 */
@PropertySource(value = {"classpath:/db.properties"})
@Configuration
public class MainConfigProfile  implements EmbeddedValueResolverAware {

    @Value("${db.user}")
    private String user;

    private StringValueResolver stringValueResolver;


    @Profile("test")
    @Bean("testDataSource")
  public DataSource dataSourceTest(@Value("${db.password}") String pwd) throws Exception {
      ComboPooledDataSource dataSource = new ComboPooledDataSource();
      dataSource.setUser(user);
      dataSource.setPassword(pwd);
      dataSource.setJdbcUrl("jdbc:mysql://192.168.56.10:3306/test");
        String s = stringValueResolver.resolveStringValue("${db.driverClass}");
        dataSource.setDriverClass(s);
      return dataSource;
  }


  @Profile("dev")
    @Bean("devDataSource")
    public DataSource dataSourceDev(@Value("${db.password}") String pwd) throws Exception {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pwd);
        dataSource.setJdbcUrl("jdbc:mysql://192.168.56.10:3306/dev");
        String s = stringValueResolver.resolveStringValue("${db.driverClass}");
        dataSource.setDriverClass(s);
        return dataSource;
    }


    @Profile("prod")
    @Bean("prodDataSource")
    public DataSource dataSourceProd(@Value("${db.password}") String pwd) throws Exception {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pwd);
        dataSource.setJdbcUrl("jdbc:mysql://192.168.56.10:3306/prod");
        String s = stringValueResolver.resolveStringValue("${db.driverClass}");
        dataSource.setDriverClass(s);
        return dataSource;
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver stringValueResolver) {
        this.stringValueResolver = stringValueResolver;
    }
}
