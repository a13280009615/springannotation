package com.zf.controller;

import com.zf.service.DeferredResultQueue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.UUID;
import java.util.concurrent.Callable;

/**
 * @author 郑凡
 * @create 2020-09-29 18:20
 */
@Controller
public class AsyncController {


    /***
     *   1 控制器 返回Callable
     *   2 springMVC异步处理 将Callable提交到TaskExecutor 使用一个隔离的线程进行执行
     *   3 DispatcherServlet 和Filter退出web容器的线程  但是 response 保持打开状态
     *   4 Callable 返回结果 springMVC 将请求重新派发给容器恢复之前的处理
     *   5 DispatcherServlet 根据 Callable返回的结果 继续进行视图渲染
     *
     *
     *    异步拦截器
     *     1 原生api的AsyncListener
     *     2 spring MVC 实现 AsyncHandlerInterceptor
     * @return
     */

    @ResponseBody
    @RequestMapping("/async01")
    public Callable<String> async01(){
        System.out.println("主线程"+Thread.currentThread()+"时间"+System.currentTimeMillis());
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("开启新线程"+Thread.currentThread()+"时间"+System.currentTimeMillis());
                Thread.sleep(2000);
                System.out.println("结束新线程"+Thread.currentThread()+"时间"+System.currentTimeMillis());

                return " Callable<String> async01";
            }
        };
        System.out.println("主线程"+Thread.currentThread()+"时间"+System.currentTimeMillis());
        return  callable;
    }


    @ResponseBody
    @RequestMapping("/createOrder")
    public DeferredResult<Object> createOrder(){
         DeferredResult<Object> deferredResult  = new DeferredResult<>(3000L,"create Fail");
        DeferredResultQueue.save(deferredResult);
         return  deferredResult;
    }

    @ResponseBody
    @RequestMapping("/create")
    public String create(){
        //创建订单
        String order = UUID.randomUUID().toString();
        DeferredResult<Object> deferredResult = DeferredResultQueue.get();
        deferredResult.setResult(order);
        return  "success==========>"+order;
    }
}
