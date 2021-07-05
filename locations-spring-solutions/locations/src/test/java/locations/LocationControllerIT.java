package locations;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class LocationControllerIT {

    LocationsService locationsService = new LocationsService();

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
