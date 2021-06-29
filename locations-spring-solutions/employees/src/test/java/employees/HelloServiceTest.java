package employees;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class HelloServiceTest {

    HelloService service;

    @Test
    void sayHello() {
        service = new HelloService();
        assertThat(service.sayHello()).startsWith("Hello world (Service) ");
    }
}