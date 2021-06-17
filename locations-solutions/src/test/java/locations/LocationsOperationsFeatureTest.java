package locations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocationsOperationsFeatureTest {
    Object[][] values;

    @BeforeEach
    void setUp() {
        Location bp = new Location("Budapest",47.49838,19.04051);
        Location deb = new Location("Debrecen",47.531693,21.626313);
        Location sc = new Location("Santa Cruz de la Sierra",0,-0);
        Location ca = new Location("Caminho des Árvores",-12.981,-38.459);
        Location bo = new Location("Boundja",0,0);

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
}
