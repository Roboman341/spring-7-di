package guru.springframework.spring7di.services;

/**
 * Created by jt, Spring Framework Guru.
 */
public class GreetingServiceImpl implements GreetingService {
    @Override
    public String sayGreeting() {
        return "Hello Everyone From Base Service!!!";
    }
}
