package locations;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.Any;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationsControllerTest {

    @Mock
    LocationsService locationsService;

    @InjectMocks
    LocationsController locationsController = new LocationsController(locationsService);

    @Test
    void getLocations() {
        when(locationsService.getLocations())
                .thenReturn(List.of(
                        new Location(1L,"Budapest",43.112,19.227) ,
                        new Location(1L,"Pécs",43.112,19.227)
                        )
                );
        assertThat(locationsController.getLocations()).isEqualTo("[Location(id=1, name=Budapest, lat=43.112, lon=19.227), Location(id=1, name=Pécs, lat=43.112, lon=19.227)]");
    }
}