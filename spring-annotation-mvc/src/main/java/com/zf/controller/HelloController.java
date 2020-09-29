package com.zf.controller;

import com.zf.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 郑凡
 * @create 2020-09-29 16:36
 */
@Controller
public class HelloController {

    @Autowired
    HelloService helloService;

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        String s = helloService.sayHello("tomcat");
        return  s;
    }

    @RequestMapping("/success")
    public  String success(){
        return "success";
    }
}
