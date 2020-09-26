package com.zf.condition;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author zhengfan
 * @create 2020-09-26 下午7:58
 * 判断是否是windows
 */
public class WindowsCondition  implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment environment = context.getEnvironment();
        String property = environment.getProperty("os.name");
        BeanDefinitionRegistry registry = context.getRegistry();
        boolean b = registry.containsBeanDefinition("person");

        if (property.equals("Windows10")){
            return  true;
        }
        return false;
    }
}
