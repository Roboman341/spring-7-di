package guru.springframework.spring7di.controllers;

import org.springframework.stereotype.Controller;

/**
 * Created by jt, Spring Framework Guru.
 */
@Controller
public class MyController {

    public String sayHello(){
        System.out.println("I'm in the controller");

        return "Hello Everyone!!!";
    }
}
