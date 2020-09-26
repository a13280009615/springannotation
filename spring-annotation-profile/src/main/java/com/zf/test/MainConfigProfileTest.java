package com.zf.test;

import com.zf.config.MainConfigProfile;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import javax.sql.DataSource;

/**
 * @author zhengfan
 * @create 2020-09-27 0:17
 */
public class MainConfigProfileTest {


    @Test
    public void  test(){
        AnnotationConfigApplicationContext ioc = new AnnotationConfigApplicationContext(MainConfigProfile.class);
        String[] names = ioc.getBeanNamesForType(DataSource.class);
        for (String name:names) {
            System.out.println(name);

        }
    }

    /**
     * 使用代码的方式 切换环境 创建 ioc 容器的时候 不能使用 有参构造器
     */

    @Test
    public void  test2(){
        //创建ioc 对象
        AnnotationConfigApplicationContext ioc = new AnnotationConfigApplicationContext();
        //设置需要激活的环境
        ConfigurableEnvironment environment = ioc.getEnvironment();
        environment.setActiveProfiles("test","dev");
        //注册主配置类
        ioc.register(MainConfigProfile.class);
        //启动刷新容器
        ioc.refresh();

        String[] names = ioc.getBeanNamesForType(DataSource.class);
        for (String name:names) {
            System.out.println(name);

        }
    }
}
