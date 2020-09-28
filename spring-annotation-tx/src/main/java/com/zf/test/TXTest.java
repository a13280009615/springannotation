package com.zf.test;

import com.zf.config.TxConfig;
import com.zf.service.UserService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author zhengfan
 * @create 2020-09-28 22:48
 */
public class TXTest {

    @Test
    public  void  test(){
        AnnotationConfigApplicationContext ioc = new AnnotationConfigApplicationContext(TxConfig.class);

        UserService userService = ioc.getBean(UserService.class);
        userService.insertUser();
        ioc.close();
    }
}
