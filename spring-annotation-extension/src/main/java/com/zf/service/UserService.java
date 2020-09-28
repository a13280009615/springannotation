package com.zf.service;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * @author zhengfan
 * @create 2020-09-29 0:57
 */
@Service
public class UserService {

    @EventListener({ApplicationEvent.class})
    public void listener(ApplicationEvent event){
        System.out.println("UserService监听到的事件"+event);
    }
}
