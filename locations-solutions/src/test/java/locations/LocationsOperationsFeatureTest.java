package locations;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;


import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class LocationsOperationsFeatureTest {
    static Object[][] values;
    static Location bp;
    static Location deb;
    static Location sc;
    static Location ca;
    static Location bo;

    @BeforeAll
    static void init() {
        bp = new Location("Budapest",47.49838,19.04051);
        deb = new Location("Debrecen",47.531693,21.626313);
        sc = new Location("Santa Cruz de la Sierra",0,-0);
        ca = new Location("Caminho des Árvores",-12.981,-38.459);
        bo = new Location("Boundja",0,0);

        values = new Object[][]{
                {bp,Boolean.FALSE}
                ,{deb, Boolean.FALSE}
                ,{sc,Boolean.TRUE}
                ,{ca, Boolean.FALSE}
                ,{bo,Boolean.TRUE}
        };
    }

    @RepeatedTest(value = 5,name = "Is on equator  {currentRepetition}/{totalRepetitions}")
    void repeatedIsOnEquator(RepetitionInfo repetitionInfo) {
        assertEquals( values[repetitionInfo.getCurrentRepetition()-1][1], ((Location) values[repetitionInfo.getCurrentRepetition()-1][0]).isEquator());
    }

    @ParameterizedTest (name = "on prime meridian {0}")
    @MethodSource("createPairs")
    void parametrizedOnPrimeMeridian(Location location, boolean expected) {
        assertEquals(expected, location.isOnPrimeMeridian());
    }

    static Stream<Arguments> createPairs() {
        return Stream.of(
                arguments(bp,false),
                arguments(deb,false),
                arguments(sc,true),
                arguments(ca,false),
                arguments(bo,true)
        );
    }
    @ParameterizedTest (name = "on prime meridian {0} / {3}")
    @CsvFileSource(resources = "/location_test_source.csv")
    void parametrizedOnPrimeMeridian(String name, double lat, double lon, boolean expected) {
        assertEquals(expected, new Location(name,lat,lon).isOnPrimeMeridian());
    }


}
