package locations;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LocationControllerRestTemplateIT {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    LocationsService locationsService;

    @Test
    void testCreateLocation() {
        LocationDTO locationDTO =
                testRestTemplate.postForObject("/api/locations",
                        new CreateCommand("Győr",
                                43.6989,
                                15.5569)
                        ,LocationDTO.class
                );
        assertEquals(locationDTO, new LocationDTO("Győr", 43.6989, 15.5569));
    }

    @Test
    void testFindLocationById() {
        LocationDTO locationDTO = testRestTemplate.getForObject("/api/locations/1", LocationDTO.class);
        assertEquals(locationDTO, new LocationDTO("Budapest",43.112,19.227));
    }

    @Test
    void testGetLocations() {
        locationsService.deleteAllLocation();

        testRestTemplate.postForObject("/api/locations",
                        new CreateCommand(
                                "Győr",
                                43.6989,
                                15.5569)
                        ,LocationDTO.class
        );

        testRestTemplate.postForObject("/api/locations",
                        new CreateCommand(
                                "Budapest",
                                43.6989,
                                15.5569)
                        ,LocationDTO.class
        );

        List<LocationDTO> found = testRestTemplate.exchange(
                "/api/locations",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<LocationDTO>>() {})
                .getBody();

        assertThat(found.get(1)).isEqualTo(new LocationDTO("Budapest", 43.6989, 15.5569));

        System.out.println(found);

        assertThat(found)
                .extracting(LocationDTO::getName)
                .containsExactly("Győr","Budapest");

    }

}
