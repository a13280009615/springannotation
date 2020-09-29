package com.zf.servletInitializer;

import com.zf.config.RootConfig;
import com.zf.config.WebConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author 郑凡
 * @create 2020-09-29 16:28
 * web容器启动的时候创建对象 调用方法来初始化以前前端控制器
 */
public class MyAbstractAnnotationConfigDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    // 获取根容器的配置类
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    // 获取web容器的配置类 spring mvc的配置文件 子容器
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    // 获取servlet 的映射信息  拦截所有请求
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
