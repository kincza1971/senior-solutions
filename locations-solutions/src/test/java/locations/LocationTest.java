package locations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationTest {
    LocationParser parser;

    @BeforeEach
    void init() {
        parser = new LocationParser();
    }

    @Test
    @DisplayName("test Constructor exception")

    void testLocation() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> new Location("Budapest",150,0));
        assertEquals("Latitude must be between -90 and 90",ex.getMessage());
        ex = assertThrows(IllegalArgumentException.class, () -> new Location("Budapest",21,270));
        assertEquals("Longitude must be between -180 and 180",ex.getMessage());
    }

    @Test
    @DisplayName("test pasrse() with valid data")
    void testParse() { //valid data
        assertEquals(new Location("Budapest",47.497912,19.040235),parser.parse("Budapest,47.497912,19.040235"));
    }

    @Test
    @DisplayName("test pasrse() with valid data - assertAll")
    void testParseAll() { //valid data
        Location l1 = new Location("Budapest",47.497912,19.040235);
        Location l2 = parser.parse("Budapest,47.497912,19.040235");
        assertAll(
                () -> assertEquals(l1,l2),
                () -> assertEquals("Budapest", l2.getName()),
                () -> assertEquals(47.497912, l2.getLat()),
                () -> assertEquals(19.040235,l2.getLon())
        );
    }

    @Test
    @DisplayName("test Parse with null argument")
    void testParseWithNull() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> parser.parse(null));
        assertEquals("Location data string must not null or empty", ex.getMessage());
    }

    @Test
    @DisplayName("test Parse with empty argument")
    void testParseWithEmpty() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> parser.parse(""));
        assertEquals("Location data string must not null or empty", ex.getMessage());
    }

    @Test
    @DisplayName("test isOnEquator (true and false)")
    void isOnEquator() {
        assertTrue(new Location("Equador",0.0,97.1).isEquator());
        assertFalse(new Location("Kongo",0.3,97.1).isEquator());
    }

    @Test
    @DisplayName("test isOnEquator (true and false)")
    void isOnPrimeMeridian() {
        assertTrue(new Location("Equador",0.0,0.0).isOnPrimeMeridian());
        assertFalse(new Location("Kongo",0.3,97.1).isOnPrimeMeridian());
    }

    @Test
    @DisplayName("test distanceFrom")
    void testDistanceFrom(){
        Location bp = new Location("Budapest",47.49838,19.04051);
        Location deb = new Location("Debrecen",47.531693,21.626313);
        assertEquals(194221.93,bp.distanceFrom(deb),0.001);
    }
}