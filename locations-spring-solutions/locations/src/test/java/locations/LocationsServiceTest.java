package locations;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchDataSource;
import org.springframework.context.annotation.Bean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LocationsServiceTest {

    @Test
    void getLocations() {

        ModelMapper modelMapper = new ModelMapper();

        LocationsService service = new LocationsService(modelMapper);

        assertThat(service.getLocations()).isEqualTo(
                List.of(
                        new LocationDTO("Budapest",43.112,19.227) ,
                        new LocationDTO("Pécs",43.112,19.227)

                )
        );
    }
}