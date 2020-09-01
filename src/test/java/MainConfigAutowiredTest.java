import com.zf.bean.Boss;
import com.zf.bean.Car;
import com.zf.bean.Color;
import com.zf.config.MainConfigAutowired;
import com.zf.service.BookService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainConfigAutowiredTest {


    @Test
    public void test1(){
        AnnotationConfigApplicationContext ioc = new AnnotationConfigApplicationContext(MainConfigAutowired.class);

        BookService bookService = ioc.getBean(BookService.class);
        System.out.println(bookService);
//        BookDao bookDao = ioc.getBean(BookDao.class);
//        System.out.println(bookDao);
        ioc.close();
    }



    @Test
    public void test2(){
        AnnotationConfigApplicationContext ioc = new AnnotationConfigApplicationContext(MainConfigAutowired.class);

        Boss boss = ioc.getBean(Boss.class);
        System.out.println(boss);

        Car car = ioc.getBean(Car.class);
        System.out.println(car);


        Color color = ioc.getBean(Color.class);
        System.out.println(color);
        ioc.close();
    }

}
