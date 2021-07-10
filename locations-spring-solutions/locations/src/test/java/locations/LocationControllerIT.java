package locations;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LocationControllerIT {

    ModelMapper modelMapper = new ModelMapper();

    LocationsService locationsService = new LocationsService(modelMapper);

    @Test
    void testGetLocations() {
        LocationsController locationsController = new LocationsController(locationsService);
        assertThat(locationsController.getLocations(Optional.empty()))
                .isEqualTo(
                        List.of(
                                new LocationDTO("Budapest",43.112,19.227) ,
                                new LocationDTO("Pécs",43.112,19.227)

                        )
                );
    }

}
