package guru.springframework.spring7di.controllers;

import guru.springframework.spring7di.services.EnvironmentService;
import org.springframework.stereotype.Controller;

/**
 * Created by jt, Spring Framework Guru.
 */
@Controller
public class EnvironmentController {

    private final EnvironmentService environmentService;

    public EnvironmentController(EnvironmentService environmentService) {
        this.environmentService = environmentService;
    }

    public String getEnvironment(){
        return "You are in " + environmentService.getEnv() + " Environment";
    }
}
