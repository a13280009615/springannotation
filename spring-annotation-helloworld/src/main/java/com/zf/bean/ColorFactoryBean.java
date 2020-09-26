package com.zf.bean;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author zhengfan
 * @create 2020-09-26 下午8:41
 * 使用 spring 工厂创建bean  范型是指定类型
 */
public class ColorFactoryBean implements FactoryBean<Color> {
    @Override
    public Color getObject() throws Exception {
        return new Color();
    }

    @Override
    public Class<?> getObjectType() {
        return Color.class;
    }

    /**
     * 返回 true 表示 这个bean 在ioc 容器中被保存一份
     *  false 表示每次这个bean 会创建一个新的bean
     * @return
     */
    @Override
    public boolean isSingleton() {
        return true;
    }
}
