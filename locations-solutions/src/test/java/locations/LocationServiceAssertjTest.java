package locations;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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
    void LocationTest() {
        Location location = locations.get(0);
        assertThat(location.getName()).isEqualTo("Budapest");
        assertThat(location.getName()).startsWith("Bud").endsWith("apest");
        assertThat(location.getLon()).isGreaterThan(0.0);
    }

}
