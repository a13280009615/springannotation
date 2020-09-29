package com.zf.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 郑凡
 * @create 2020-09-29 17:26\
 * servlet 3.0  asyncSupported = true 支持异步
 */
@WebServlet(value = "/async",asyncSupported = true)
public class HelloAsynServlet  extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //开启异步
        System.out.println("主线程为"+Thread.currentThread());

        //开启异步模式
        AsyncContext asyncContext = req.startAsync();

        //开启异步处理
        asyncContext.start(new Runnable() {
            @Override
            public void run() {
                try {
                    sayHello();
                    asyncContext.complete();
                    //获取到异步的上下文
                    AsyncContext context = req.getAsyncContext();
                    //获取响应
                    ServletResponse response = context.getResponse();
                    response.getWriter().write("hello  async");

                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void  sayHello() throws InterruptedException {
        System.out.println(Thread.currentThread()+"process...");
        Thread.sleep(3000);
    }
}
