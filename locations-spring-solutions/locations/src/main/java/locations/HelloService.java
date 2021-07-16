package locations;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor

public class HelloService {

    private HelloProperties helloProperties;


    public String sayHello() {
        return helloProperties.getMessage() + " " + LocalDateTime.now();
    }
}
