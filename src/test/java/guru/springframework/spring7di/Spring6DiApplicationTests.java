package guru.springframework.spring7di;

import guru.springframework.spring7di.controllers.MyController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class Spring6DiApplicationTests {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    MyController myController;

    // run this test. This is Spring's dependency injection in action
    // providing the Bean inside the Spring context
    @Test
    void testAutowireOfController() {
        System.out.println(myController.sayHello());
    }

    @Test
    void testGetControllerFromCtx() { // the same thing in a different way
        MyController myController = applicationContext.getBean(MyController.class);

        System.out.println(myController.sayHello());
    }

    @Test
    void contextLoads() {
    }

}
