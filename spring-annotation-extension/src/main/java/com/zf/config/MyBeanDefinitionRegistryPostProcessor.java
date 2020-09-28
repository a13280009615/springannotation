package com.zf.config;

import com.zf.bean.Red;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author zhengfan
 * @create 2020-09-28 23:53
 */
@Component
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    @Override
    /**
     *  BeanDefinitionRegistry
     *   bean的定义信息的保存中心 以后beanFactory就会按照 BeanDefinitionRegistry里面
     *   保存的每一个定义信息创建bean实例
     */

    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
         System.out.println("MyBeanDefinitionRegistryPostProcessor.postProcessBeanDefinitionRegistry()===>bean的数量"+registry.getBeanDefinitionCount());
        //RootBeanDefinition beanDefinition = new RootBeanDefinition(Red.class);
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.rootBeanDefinition(Red.class).getBeanDefinition();
        registry.registerBeanDefinition("red",beanDefinition);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("MyBeanDefinitionRegistryPostProcessor.postProcessBeanFactory()=>>>bean的数量"+beanFactory.getBeanDefinitionCount());
    }
}
