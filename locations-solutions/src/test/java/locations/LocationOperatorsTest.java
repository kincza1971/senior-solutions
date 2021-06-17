package locations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LocationOperatorsTest {

    LocationOperators lop = new LocationOperators();
    List<Location> locs;

    @BeforeEach
    void setUp() {
        Location bp = new Location("Budapest",47.49838,19.04051);
        Location deb = new Location("Debrecen",47.531693,21.626313);
        Location sc = new Location("Santa Cruz de la Sierra",-17.78473,-63.18051);
        Location ca = new Location("Caminho des Árvores",-12.981,-38.459);
        Location bo = new Location("Boundja",-1.0981,17.459);
        locs = List.of(bp,deb,sc,ca, bo);
    }

    @Test
    @DisplayName("test filterOnNorth - add 1 by 1")
    void add() {
        locs.forEach(lop::addLocation);
        assertEquals(locs,lop.getLocations());
    }

    @Test
    @DisplayName("test filterOnNorth - addAll")
    void addAll() {
        lop.addLocations(locs);
        assertEquals(locs,lop.getLocations());
    }

    @Test
    @DisplayName("test filterOnNorth")
    void filterOnNorth() {
        lop.addLocations(locs);
        List<Location> northExpected = List.of(locs.get(0),locs.get(1));
        List<Location> northActual = lop.filterOnNorth();

        assertEquals(northExpected,northActual);

        assertAll(
                () -> assertEquals(2,northActual.size()),
                () -> assertEquals("Budapest",northActual.get(0).getName()),
                () -> assertEquals("Debrecen", northActual.get(1).getName())
        );
    }
}