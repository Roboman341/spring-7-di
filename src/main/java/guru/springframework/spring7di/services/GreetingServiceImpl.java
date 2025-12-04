package guru.springframework.spring7di.services;

public class GreetingServiceImpl implements GreetingService {
    @Override
    public String sayGreeting(String name) {
        return "Hello from base service";
    }
}
