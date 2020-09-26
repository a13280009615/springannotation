package com.zf.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author zhengfan
 * @create 2020-09-26 21:23
 */
public class Cat  implements InitializingBean, DisposableBean {

    public  Cat(){
        System.out.println("cat  construct....");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("对象被销毁了");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("对象创建完成并在属性赋值之后");
    }
}
