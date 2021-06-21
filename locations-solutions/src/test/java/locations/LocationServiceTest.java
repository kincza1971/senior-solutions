package locations;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LocationServiceTest {
    static Location bp;
    static Location deb;
    static Location sc;
    static Location ca;
    static Location bo;

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


    @Test
    void testWriteLocations() {
        Path file = tempDir.resolve("locations.csv");
        List<Location> locations = List.of(bp,deb,sc,ca,bo);
        LocationService ls = new LocationService();
        ls.writeLocations(file,locations);

        List<String> expected = locations.stream()
                .map(Location::toCsv)
                .toList();
        try {
            List<String> actual = Files.readAllLines(file);
            assertEquals(expected,actual);
            assertEquals("Budapest,47.49838,19.04051",actual.get(0));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

}