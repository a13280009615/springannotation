package com.zf.improt;

import com.zf.bean.RainBow;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    /**
     *
     * @param importingClassMetadata  当前类的注解信息
     * @param registry  bean定义的注册类
     *
     *            把所有要加载到容器中的组件 通过registry 注册bean
     *
     */
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
//        判断容器中是否 包含某个组件
        boolean b = registry.containsBeanDefinition("com.zf.bean.Blue");
        boolean b1 = registry.containsBeanDefinition("com.zf.bean.Yellow");

        if (b && b1){
//            指定bean组件的定义信息
            RootBeanDefinition beanDefinition = new RootBeanDefinition(RainBow.class);
//             注册bean
            registry.registerBeanDefinition("rainBow",beanDefinition);
        }
    }
}
