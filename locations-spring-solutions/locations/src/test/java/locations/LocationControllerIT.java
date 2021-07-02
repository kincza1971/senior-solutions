package locations;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LocationControllerIT {

    LocationsService locationsService = new LocationsService();

    @Test
    void testGetLocations() {
        LocationsController locationsController = new LocationsController(locationsService);
        assertThat(locationsController.getLocations()).isEqualTo("[Location(id=1, name=Budapest, lat=43.112, lon=19.227), Location(id=1, name=Pécs, lat=43.112, lon=19.227)]");
    }

}
