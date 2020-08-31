import com.zf.config.MainConfigLifeCycle;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanLifeCycleTest {


    @Test
    public  void  test1(){
        AnnotationConfigApplicationContext ioc = new AnnotationConfigApplicationContext(MainConfigLifeCycle.class);
        System.out.println("ioc容器创建完成");
        ioc.close();
    }
}
