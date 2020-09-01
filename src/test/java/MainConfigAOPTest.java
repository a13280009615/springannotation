import com.zf.aop.MyCalculator;
import com.zf.config.MainConfigAOP;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainConfigAOPTest {


    @Test
    public  void test1(){
        AnnotationConfigApplicationContext ioc = new AnnotationConfigApplicationContext(MainConfigAOP.class);

        MyCalculator calculator = ioc.getBean(MyCalculator.class);
        calculator.div(2,0);
    }
}
