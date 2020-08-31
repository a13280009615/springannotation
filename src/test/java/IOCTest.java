import com.zf.bean.Person;
import com.zf.config.MainConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

public class IOCTest {

    ApplicationContext ioc = new AnnotationConfigApplicationContext(MainConfig.class);

    @Test
    public  void test02(){

//        通过 类型从 ioc 容器中找到这个类型的所有组件
        String[] beanNamesForType = ioc.getBeanNamesForType(Person.class);
        for (String name:beanNamesForType) {
            System.out.println(name);
        }

        Map<String, Person> persons = ioc.getBeansOfType(Person.class);
        System.out.println(persons);

    }

    @Test
    public  void test01(){

//        容器中的组件有哪些组件 通过 getBeanDefinitionNames
        String[] beanNamesForType = ioc.getBeanDefinitionNames();

        for (String name : beanNamesForType) {
            System.out.println(name);
        }
    }



//    如果系统是 linux  就放 linux  如果 window 就放  bill gates
    @Test
    public  void test03(){

//        容器中的组件有哪些组件 通过 getBeanDefinitionNames
        String[] beanNamesForType = ioc.getBeanDefinitionNames();

        for (String name : beanNamesForType) {
            System.out.println(name);
        }
    }
}
