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

## Requirements

- Java 25 or higher
- Maven 3.9.11 or higher
- Spring Boot 4.0.0

## Running the Application

```bash
mvn spring-boot:run
```

Or run the `Spring7DiApplication` class directly from your IDE.
