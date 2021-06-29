package employees;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class EmployeesApplicationTests {

    @Autowired
    HelloController helloController;

    @Test
    void contextLoads() {
        assertThat(helloController.sayHello()).startsWith("Hello world (Service)");
    }

}
