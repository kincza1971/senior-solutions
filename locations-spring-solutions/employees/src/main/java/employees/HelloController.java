package employees;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class HelloController {
    HelloService service;

    public HelloController(HelloService service) {
        this.service = service;
    }

    @GetMapping("/")
    String sayHello() {
        return service.sayHello();
    }
}
