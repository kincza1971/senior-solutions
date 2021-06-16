package locations;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationParserTest {
    LocationParser parser;

//    @BeforeAll
//    void init() {
//        parser = new LocationParser();
//    }

    @Test
    void testParse() {
        assertEquals(new Location("Budapest",47.497912,19.040235),parser.parse("Budapest,47.497912,19.040235"));
    }
}