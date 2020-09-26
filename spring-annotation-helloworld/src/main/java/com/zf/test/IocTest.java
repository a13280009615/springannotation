package com.zf.test;

import com.zf.bean.Person;
import com.zf.config.IocConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zhengfan
 * @create 2020-09-26 下午5:08
 */
public class IocTest {
   private ApplicationContext ioc = new AnnotationConfigApplicationContext(IocConfig.class);
    /**
     * 原始 的 xml 方式
     */
    @Test
    public  void testXml() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("ioc.xml");
        Person person = (Person) ioc.getBean("person");
        System.out.println(person);
    }

    // 注解版方式 获取 ioc 容器

    @Test
    public void  test(){

      Person person = (Person) ioc.getBean("person");
        System.out.println(person);

        // 通过类型找到这些bean的名字
        String[] namesForType = ioc.getBeanNamesForType(Person.class);
        for (String name: namesForType) {
            System.out.println(name);
        }

    }

    /**
     * 测试包扫描
     */

    @Test
    public void test03(){
        String[] names = ioc.getBeanDefinitionNames();
        for (String name: names) {
            System.out.println(name);
        }
    }
}
