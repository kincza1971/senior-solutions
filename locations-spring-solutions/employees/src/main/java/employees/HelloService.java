package employees;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class HelloService {

    String sayHello() {
        return "Hello world (Service) devTools " + LocalDateTime.now();
    }
}
