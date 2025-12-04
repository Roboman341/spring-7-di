package guru.springframework.spring7di.controllers;

import guru.springframework.spring7di.services.GreetingService;

public class PropertyInjectedController {

    GreetingService greetingService;

    public String sayHello(String name) {
        return greetingService.sayGreeting(name);
    }
}
