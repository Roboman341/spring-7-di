package guru.springframework.spring7di;

import guru.springframework.spring7di.controllers.MyController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Spring7DiApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = SpringApplication.run(Spring7DiApplication.class, args);

        MyController controller = ctx.getBean(MyController.class);

        System.out.println("In main method");

        System.out.println(controller.sayHello());
    }

}
