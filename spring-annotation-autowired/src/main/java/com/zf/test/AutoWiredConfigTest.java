package com.zf.test;

import com.zf.bean.Boss;
import com.zf.bean.Car;
import com.zf.bean.Color;
import com.zf.config.AutoWiredConfig;
import com.zf.service.BookService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author zhengfan
 * @create 2020-09-26 22:36
 */
public class AutoWiredConfigTest {

    private ApplicationContext ioc   = new AnnotationConfigApplicationContext(AutoWiredConfig.class);

    @Test
    public void  test(){
        BookService bookService = (BookService) ioc.getBean("bookService");
        bookService.print();
        //同一个对象
     //   BookDao bookDao = (BookDao) ioc.getBean("bookDao");
       // System.out.println(bookDao);
    }

    /**
     *  测试 autowired 标注在 方法上 和  构造器上 和参数上
     */

    @Test
    public void  test2(){
        Boss boss = (Boss) ioc.getBean("boss");
        Car car = ioc.getBean(Car.class);
        System.out.println(boss.getCar());
        System.out.println(car);
        Color color = ioc.getBean(Color.class);
        System.out.println(color.getCar());
        System.out.println(ioc);
    }
}
