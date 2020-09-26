package com.zf.test;

import com.zf.config.ImportComponentConfig;
import com.zf.config.IocConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author zhengfan
 * @create 2020-09-26 下午8:16
 * 自定义逻辑返回要导入的组件
 */
public class ImportComponentConfigTest {


    private ApplicationContext ioc = new AnnotationConfigApplicationContext(ImportComponentConfig.class);

    @Test
    public  void  test(){
        String[] names = ioc.getBeanDefinitionNames();
        for (String name:names) {
            System.out.println(name);
        }
    }


    /**
     * 获取注册在ioc容器中的工厂bean
     * 虽然给容器中注册的是工厂bean 实际是 通过工厂bean的 getObject方法创建对象
     * 结果是一个 class com.zf.bean.Color
     * 如果想要在ioc 容器中拿到这个工厂bean 在getBean的时候加一个 &colorFactoryBean
     * BeanFactory接口定义了 工厂bean 的前缀 &
     *
     */
    @Test
    public  void  test2(){
        Object colorFactoryBean = ioc.getBean("colorFactoryBean");
        System.out.println(colorFactoryBean.getClass());
        Object bean = ioc.getBean("&colorFactoryBean");
        System.out.println(bean.getClass());
    }
}
