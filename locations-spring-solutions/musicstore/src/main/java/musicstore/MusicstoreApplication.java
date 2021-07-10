package musicstore;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MusicstoreApplication {

    @Bean
    ModelMapper modelMapper(){
        return new ModelMapper();
    };

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper().findAndRegisterModules();
    }


    public static void main(String[] args) {
        SpringApplication.run(MusicstoreApplication.class, args);
    }

}
