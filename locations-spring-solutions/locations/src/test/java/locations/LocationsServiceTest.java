package locations;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class LocationsServiceTest {

    @Test
    void getLocations() {

        ModelMapper modelMapper = new ModelMapper();
        LocationsDao locationsDao = new LocationsDao(new JdbcTemplate());

        LocationsService service = new LocationsService(modelMapper, locationsDao);

        assertThat(service.getLocations(Optional.empty())).isEqualTo(
                List.of(
                        new LocationDTO("Budapest",43.112,19.227) ,
                        new LocationDTO("Pécs",43.112,19.227)

                )
        );
    }
}