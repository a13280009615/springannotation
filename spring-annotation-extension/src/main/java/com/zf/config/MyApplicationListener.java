package com.zf.config;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author zhengfan
 * @create 2020-09-29 0:17
 */
@Component
public class MyApplicationListener implements ApplicationListener {

    /**
     * 当容器中发布此事件以后 方法触发
     * @param event
     */
    @Override
    public void onApplicationEvent(ApplicationEvent event) {

        System.out.println("收到事件" + event);

    }
}
