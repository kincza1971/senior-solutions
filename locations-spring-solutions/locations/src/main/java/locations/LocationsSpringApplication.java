package locations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LocationsSpringApplication {

//    @Bean
//    LocationsService locationsService() {
//        return new LocationsService();
//    };


    public static void main(String[] args) {
        SpringApplication.run(LocationsSpringApplication.class, args);
    }

}
