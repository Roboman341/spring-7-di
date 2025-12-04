package guru.springframework.spring7di.controllers;

import guru.springframework.spring7di.services.GreetingService;
import guru.springframework.spring7di.services.GreetingServiceImpl;
import org.springframework.stereotype.Controller;

@Controller
public class MyController {

    private final GreetingService greetingService;

    public MyController() {
        this.greetingService = new GreetingServiceImpl();
    }

    public String sayHello() {
        System.out.println("I'm in the controller");

//        return "Hello";
        return greetingService.sayGreeting("");
    }
}
