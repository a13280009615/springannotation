package com.zf.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author zhengfan
 * @create 2020-09-26 21:37
 */
@Component
public class MyBeanPostProcessor  implements BeanPostProcessor {

    /**
     *
     * @param bean  刚才创建的实例
     * @param beanName  这个实例在容器中的名字
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessBeforeInitialization..."+beanName+"===>"+bean);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessAfterInitialization..."+beanName+"===>"+bean);
        return bean;
    }
}
