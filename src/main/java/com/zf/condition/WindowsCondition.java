package com.zf.condition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class WindowsCondition  implements Condition {

    /**
     *
     * @param context  判断条件的上下文环境
     * @param metadata 当前标注了 condition 的注释信息
     * @return
     */
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {


        //获取 bean 的创建工厂
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        //获取类加载器
        ClassLoader classLoader = context.getClassLoader();
        //获取当前环境  系统变量 等等
        Environment environment = context.getEnvironment();
        String property = environment.getProperty("os.name");
        //获取 到bean定义的
        BeanDefinitionRegistry registry = context.getRegistry();

//        可以判断容器中bean注册情况
        registry.containsBeanDefinition("person");

        if (property.contains("Windows")){
            return true;
        }
        return false;
    }
}
