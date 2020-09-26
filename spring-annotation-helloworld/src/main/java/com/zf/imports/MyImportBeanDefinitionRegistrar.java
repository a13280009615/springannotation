package com.zf.imports;

import com.zf.bean.RainBow;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author zhengfan
 * @create 2020-09-26 下午8:31
 * 自定义
 */
public class MyImportBeanDefinitionRegistrar  implements ImportBeanDefinitionRegistrar {
    /**
     *
     * @param importingClassMetadata  当前类的注解信息
     * @param registry  bean定义的注册类
     *                  把所有要添加到的容器的bean 通过registry方法注册到容器
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        boolean b = registry.containsBeanDefinition("com.zf.bean.Red");
        boolean b1 = registry.containsBeanDefinition("com.zf.bean.Yellow");

        if (b && b1){
            //指定bean的注册信息 (定义bean的类型)
            RootBeanDefinition beanDefinition = new RootBeanDefinition(RainBow.class);
            //注册一个bean 指定bean的id
            registry.registerBeanDefinition("rainbow",beanDefinition);
        }
    }
}
