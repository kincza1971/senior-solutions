package locations;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.Any;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationsControllerTest {

    @Mock
    LocationsService locationsService;

    @InjectMocks
    LocationsController locationsController = new LocationsController(locationsService);

    @Test
    void getLocations() {
        when(locationsService.getLocations(any()))
                .thenReturn(List.of(
                        new LocationDTO("Budapest",43.112,19.227) ,
                        new LocationDTO("Pécs",43.112,19.227)
                        )
                );
        assertThat(locationsController.getLocations(Optional.empty())).isEqualTo(
                List.of(
                        new LocationDTO("Budapest",43.112,19.227) ,
                        new LocationDTO("Pécs",43.112,19.227)
                )
        );
    }
}