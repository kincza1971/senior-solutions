package locations;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.util.List;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.AllOf.allOf;

public class LocationServiceHamcrestTest {
    static Location bp;
    static Location deb;
    static Location sc;
    static Location ca;
    static Location bo;
    static List<Location> locations;

    @TempDir
    Path tempDir;

    @BeforeAll
    static void init() {
        bp = new Location("Budapest",47.49838,19.04051);
        deb = new Location("Debrecen",47.531693,21.626313);
        sc = new Location("Santa Cruz de la Sierra",0,-0);
        ca = new Location("Caminho des Árvores",-12.981,-38.459);
        bo = new Location("Boundja",0,0);
    }
    @BeforeEach
    void setup() {
        locations = List.of(bp,deb,sc,ca,bo);
    }

    @Test
    @DisplayName("loadLocations Hamcrest")
    void loadLocationsTest() {
        LocationService locationService = new LocationService();
        Path file = tempDir.resolve("locations.csv");

        locationService.writeLocations(file,locations);

        List<Location> actual = locationService.loadLocations(file);

        assertThat(actual.get(0), allOf(
                hasProperty("name",equalTo("Budapest")),
                hasProperty("lat",equalTo(47.49838)),
                hasProperty("lon",equalTo(19.04051))
                )
        );

        assertThat(actual.get(4), allOf(
                hasProperty("name",equalTo("Boundja")),
                hasProperty("lat",equalTo(0.0)),
                hasProperty("lon",equalTo(0.0))
                )
        );

        assertThat(locations,equalTo(actual));

        assertThat(actual.get(0),instanceOf(Location.class));

//        assertThat(actual,hasItem(hasProperty("name",startsWith("Boundj"))));

        assertThat(actual,hasItem(
//                hasProperty("name",startsWith("Boundj"))
                LocationWithZeroCoordinate.locationWithZeroCoordinate(equalTo(0.0))
                )
        );
    }
}
