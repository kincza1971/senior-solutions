package locations;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LocationsServiceTest {

    @Test
    void getLocations() {
        LocationsService service = new LocationsService();
        assertThat(service.getLocations()).isEqualTo(
                List.of(
                        new Location(1L,"Budapest",43.112,19.227) ,
                        new Location(1L,"Pécs",43.112,19.227)

                )
        );
    }
}