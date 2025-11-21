package guru.springframework.spring7di.controllers;

import guru.springframework.spring7di.services.GreetingService;

/**
 * Created by jt, Spring Framework Guru.
 */
public class PropertyInjectedController {

    GreetingService greetingService;

    public String sayHello(){
        return greetingService.sayGreeting();
    }

}
