package com.zf.config;

import com.zf.filter.UserFilter;
import com.zf.listener.UserListener;
import com.zf.service.HelloService;
import com.zf.servlet.UserServlet;

import javax.servlet.*;
import javax.servlet.annotation.HandlesTypes;
import java.util.EnumSet;
import java.util.Set;

/**
 * @author 郑凡
 * @create 2020-09-29 15:15
 * @HandlesTypes(value = {}) : 容器启动的时候  将@HandlesTypes 指定的这个类型下面的子类
 * 实现类 子接口 传递过来
 */
@HandlesTypes(value = {HelloService.class})
public class MyServletContainerInitializer implements ServletContainerInitializer {
    /**
     *  应用启动的时候 会运行 onStartup方法
     * @param set  就是 @HandlesTypes 传递过来的所有类型及子类型
     * @param servletContext  代表当前web应用的ServletContext  注册web组件
     *                        可以注册组件  servlet  listener  filter
     * @throws ServletException
     *
     * 使用编码的方式 在项目启动的时候给servletContext里面添加组件
     *   必须在项目启动的时候来添加
     *    1) ServletContainerInitializer 得到servletContext
     *    2) 通过ServletContextListener 得到servletContext
     */
    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        System.out.println("感兴趣的类型");
        for (Class clazz:set) {
            System.out.println(clazz);
        }
        // 注册组件
        ServletRegistration.Dynamic userServlet = servletContext.addServlet("userServlet", new UserServlet());
        userServlet.addMapping("/user");

        //注册 监听器
        servletContext.addListener(UserListener.class);

        //注册监听器
        FilterRegistration.Dynamic userFilter = servletContext.addFilter("userFilter", UserFilter.class);
        // 配饰filter的映射信息
        userFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST),true,"/*");

    }
}
