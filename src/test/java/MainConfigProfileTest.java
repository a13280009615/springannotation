import com.zf.config.MainConfigProfile;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

public class MainConfigProfileTest {


    @Test
    public  void  test1(){
        AnnotationConfigApplicationContext ioc = new AnnotationConfigApplicationContext(MainConfigProfile.class);

        String[] names = ioc.getBeanNamesForType(DataSource.class);
        for (String name : names) {
            System.out.println(name);
        }
        ioc.close();
    }


    @Test
    public  void  test2(){
        //        创建容器对象
        AnnotationConfigApplicationContext ioc = new AnnotationConfigApplicationContext();
        //  设置需要激活的环境 可以激活多个环境
        ioc.getEnvironment().setActiveProfiles("test");
        //注册主配置类
        ioc.register(MainConfigProfile.class);
        //        启动刷新容器
        ioc.refresh();

        String[] names = ioc.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }

        ioc.close();
    }
}
