# Spring 7 DI - Understanding the Spring Context

This project demonstrates the fundamental concepts of Spring's IoC (Inversion of Control) container and how the Spring Application Context works.

## Project Overview

This is a **Spring Boot 4.0.0** application (using Spring Framework 7 under the hood) that demonstrates the fundamental concept of the Spring Application Context and bean management.

## How the Spring Context Works

### 1. **Application Bootstrap** (Spring7DiApplication.java:13)
```java
ConfigurableApplicationContext ctx = SpringApplication.run(Spring7DiApplication.class, args);
```

This line does several critical things:
- **Creates the Spring Application Context** - This is the IoC container that manages all your beans
- **Component Scanning** - Because of `@SpringBootApplication` on line 8, Spring scans the package `guru.springframework.spring7di` and all sub-packages for components
- **Bean Registration** - Spring finds classes annotated with stereotype annotations (`@Controller`, `@Service`, `@Component`, etc.) and registers them as beans

### 2. **Bean Discovery** (MyController.java:5)
```java
@Controller
public class MyController {
```

The `@Controller` annotation tells Spring:
- "This class should be managed by the Spring container"
- Spring creates an instance (bean) of `MyController` and stores it in the application context
- The bean is a **singleton** by default (one instance shared across the application)

### 3. **Bean Retrieval** (Spring7DiApplication.java:17)
```java
MyController controller = ctx.getBean(MyController.class);
```

Here you're asking the Spring context:
- "Give me the bean of type `MyController`"
- Spring looks up the bean it created during component scanning
- Returns the managed instance

### 4. **Using the Bean** (Spring7DiApplication.java:19)
```java
System.out.println(controller.sayHello());
```

You're now using the Spring-managed bean to call its method.

## Key Concepts Demonstrated

### Spring Application Context (`ConfigurableApplicationContext`)
- Central interface for providing configuration to an application
- Bean factory that creates, configures, and manages beans
- Maintains the complete lifecycle of beans

### @SpringBootApplication
- Combines three annotations: `@Configuration`, `@EnableAutoConfiguration`, and `@ComponentScan`
- Enables component scanning from the package where the main class is located

### Component Scanning
- Spring automatically finds classes with stereotype annotations (`@Controller`, `@Service`, `@Repository`, `@Component`)
- Registers them as beans in the context

### Inversion of Control (IoC)
- Instead of manually creating `new MyController()`, you let Spring manage the object lifecycle
- You retrieve the object from the context when needed

## Execution Flow

1. Application starts → `SpringApplication.run()` called
2. Spring creates the application context
3. Component scanning finds `MyController` (has `@Controller`)
4. Spring instantiates `MyController` and stores it as a bean
5. "In main method" is printed
6. `ctx.getBean(MyController.class)` retrieves the bean
7. `controller.sayHello()` executes → prints "I'm in the controller" and returns "Hello"
8. "Hello" is printed to console

## Why This Matters

This simple example is the foundation for understanding Spring's dependency injection - in more complex applications, beans would have dependencies on other beans, and Spring would automatically wire them together!

## Understanding Dependency Injection in Java

Dependency Injection (DI) is a design pattern where objects receive their dependencies from external sources rather than creating them internally. Instead of a class creating the objects it needs, those objects are "injected" into it.

### Without Dependency Injection
```java
public class MyController {
    private MyService service;

    public MyController() {
        this.service = new MyService();  // Controller creates its own dependency
    }
}
```

**Problems:**
- Tight coupling - `MyController` is hardcoded to use `MyService`
- Hard to test - Can't easily swap `MyService` with a mock
- Hard to change - If you want a different implementation, you must modify the constructor

### With Dependency Injection
```java
public class MyController {
    private MyService service;

    public MyController(MyService service) {
        this.service = service;  // Dependency is injected from outside
    }
}
```

**Benefits:**
- Loose coupling - `MyController` doesn't know how `MyService` is created
- Easy to test - You can inject a mock `MyService` in tests
- Flexible - You can inject different implementations without changing `MyController`

## Three Types of Dependency Injection in Java

### 1. Constructor Injection (Recommended)
```java
@Controller
public class MyController {
    private final MyService service;

    @Autowired  // Optional in Spring if there's only one constructor
    public MyController(MyService service) {
        this.service = service;
    }
}
```

**Advantages:**
- Dependencies are required and immutable (using `final`)
- Objects are fully initialized when created
- Easy to test - just pass dependencies to constructor
- Makes dependencies explicit and visible
- **This is the recommended approach in Spring**

### 2. Setter Injection
```java
@Controller
public class MyController {
    private MyService service;

    @Autowired
    public void setService(MyService service) {
        this.service = service;
    }
}
```

**Advantages:**
- Allows optional dependencies
- Can change dependencies after object creation
- Useful for optional or reconfigurable dependencies

**Disadvantages:**
- Objects can be in partially initialized state
- Dependencies can be changed, leading to potential issues

### 3. Field Injection
```java
@Controller
public class MyController {
    @Autowired
    private MyService service;
}
```

**Advantages:**
- Most concise syntax
- Less boilerplate code

**Disadvantages:**
- Cannot use `final` fields
- Hard to test - requires reflection or Spring context
- Hides dependencies - not obvious what the class needs
- **Not recommended for production code**

### Example: How Spring Does Dependency Injection

Here's what it would look like if you added a service to your current project:

```java
// Service layer
@Service
public class GreetingService {
    public String getGreeting() {
        return "Hello from Service!";
    }
}

// Controller with dependency
@Controller
public class MyController {
    private final GreetingService greetingService;

    public MyController(GreetingService greetingService) {
        this.greetingService = greetingService;  // Spring injects this
    }

    public String sayHello() {
        return greetingService.getGreeting();
    }
}
```

**When Spring creates the application context:**
1. It finds `GreetingService` (marked with `@Service`) and creates a bean
2. It finds `MyController` (marked with `@Controller`)
3. It sees `MyController` needs a `GreetingService` in its constructor
4. It automatically injects the `GreetingService` bean into `MyController`

**This is the "magic" of Spring** - you never write `new GreetingService()` or `new MyController()`. Spring manages all of this for you through its IoC container!

## The Same Works in Tests!

The Spring context works exactly the same way in tests. See `Spring6DiApplicationTests.java` for examples:

### Approach 1: Using @Autowired (Spring6DiApplicationTests.java:12-16)
```java
@Autowired
ApplicationContext applicationContext;

@Autowired
MyController myController;
```

With `@SpringBootTest`, Spring creates a test application context and can inject beans directly into your test class using `@Autowired`. This is the most common approach in Spring tests.

### Approach 2: Using getBean() (Spring6DiApplicationTests.java:24-25)
```java
@Test
void testGetControllerFromCtx() {
    MyController myController = applicationContext.getBean(MyController.class);
    System.out.println(myController.sayHello());
}
```

Just like in the main application, you can also retrieve beans from the context using `getBean()` in tests.

### Key Testing Annotations

**@SpringBootTest** (Spring6DiApplicationTests.java:9)
- Loads the full Spring application context for integration testing
- Performs component scanning just like the main application
- Creates all beans defined in your application

Both approaches work with the same Spring context - the difference is just how you access the beans!

## Requirements

- Java 25 or higher
- Maven 3.9.11 or higher
- Spring Boot 4.0.0

## Running the Application

```bash
mvn spring-boot:run
```

Or run the `Spring7DiApplication` class directly from your IDE.



PaymentService is the interface, enabling flexibility.
The Order class has a private final paymentService property.
The dependency is injected via the constructor, ensuring it cannot be changed after instantiation.
This setup makes the code easier to test and maintain, adhering to best practices.

```java
// Define an interface for the dependency
public interface PaymentService {
void processPayment();
}

// Implement the interface
public class CreditCardPaymentService implements PaymentService {
@Override
public void processPayment() {
System.out.println("Processing credit card payment");
}
}

// Main class with dependency injection
public class Order {
private final PaymentService paymentService;

    // Constructor injection
    public Order(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void completeOrder() {
        paymentService.processPayment();
        System.out.println("Order completed");
    }
}

// Usage
public class Main {
public static void main(String[] args) {
PaymentService paymentService = new CreditCardPaymentService();
Order order = new Order(paymentService);
order.completeOrder();
}
}
```