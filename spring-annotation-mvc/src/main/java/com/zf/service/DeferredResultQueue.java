package com.zf.service;

import org.springframework.web.context.request.async.DeferredResult;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author 郑凡
 * @create 2020-09-29 18:44
 */
public class DeferredResultQueue {

    private  static Queue<DeferredResult<Object>> queue = new ConcurrentLinkedDeque<>();


    public static  void  save(DeferredResult<Object> deferredResult){
         queue.add(deferredResult);
    }

    public static  DeferredResult<Object>  get(){
        return queue.poll();
    }

}
