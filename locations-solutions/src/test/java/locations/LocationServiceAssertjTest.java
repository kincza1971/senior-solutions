package locations;

import org.assertj.core.api.Condition;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;

public class LocationServiceAssertjTest {
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
        bp = new Location("Budapest", 47.49838, 19.04051);
        deb = new Location("Debrecen", 47.531693, 21.626313);
        sc = new Location("Santa Cruz de la Sierra", 0, -0);
        ca = new Location("Caminho des Árvores", -12.981, -38.459);
        bo = new Location("Boundja", 0, 0);
    }


    @BeforeEach
    void setup() {
        locations = List.of(bp, deb, sc, ca, bo);
    }

    @Test
    void LocationTest() {
        Location location = locations.get(0);
        assertThat(location.getName()).isEqualTo("Budapest");
        assertThat(location.getName()).startsWith("Bud").endsWith("apest");
        assertThat(locations).filteredOn(l -> l.getLon() == 0.0 || l.getLat() == 0.0)
                .containsOnly(sc, bo);
    }

    @Test
    void loadedLocationTest() {
        Path file = tempDir.resolve("locations.csv");
        LocationService locationService = new LocationService();
        locationService.writeLocations(file,locations);
        List<Location> actual = locationService.loadLocations(file);
        assertThat(actual).filteredOn(l -> l.getLon() == 0.0 || l.getLat() == 0.0)
                .containsOnly(sc, bo);
        assertThat(sc.getLat() == 0 || sc.getLon() == 0).isEqualTo(true);
    }

    @ExtendWith(SoftAssertionsExtension.class)
    @Test
    void softTest(SoftAssertions soft) {
        soft.assertThat(bo.getName()).startsWith("Boun");
        soft.assertThat(bo.getName()).doesNotEndWith("pest");
        soft.assertThat(bo.getName()).as("rossz név %s / %s","Budapest","Boundja").isEqualTo("Boundja");
        soft.assertThat(bo.isEquator()).as("is on equator?").isEqualTo(true);
    }


    @Test
    void testWithCondition() {
        Condition<Location> startsWithB = new Condition<>(l -> l.getName().startsWith("B"), "B betüs városok");
        assertThat(locations)
                .filteredOn(startsWithB)
                .size().isEqualTo(2);
        assertThat(locations)
                .filteredOn(startsWithB)
                .extracting(Location::getName)
                .containsOnly("Budapest","Boundja");
    }
    @Test

    void testWithZeroCoordinate() {
        Condition<Location> hasZeroCoordinate =
                new Condition<>(l ->l.getLat() == 0.0 || l.getLon() == 0.0, "Locations with at least one zero coordinate");
        assertThat(locations).areExactly(2,hasZeroCoordinate);

        assertThat(locations)
                .filteredOn(hasZeroCoordinate)
                .extracting(Location::getName)
                .containsOnly("Santa Cruz de la Sierra","Boundja");
    }
}
