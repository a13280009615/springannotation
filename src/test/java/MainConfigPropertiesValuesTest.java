import com.zf.bean.Person;
import com.zf.config.MainConfigPropertiesValues;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author zhengfan
 * @create 2020-08-31 23:12
 */
public class MainConfigPropertiesValuesTest {

@Test
    public  void  test(){
        AnnotationConfigApplicationContext ioc = new AnnotationConfigApplicationContext(MainConfigPropertiesValues.class);
    Person person = (Person) ioc.getBean("person");
    System.out.println(person);
}
}
