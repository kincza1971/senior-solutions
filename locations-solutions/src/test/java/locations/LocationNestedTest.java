package locations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LocationNestedTest {
    LocationParser parser;
    @BeforeEach
    void setup() {
        parser = new LocationParser();

    }

    @Nested
    public class LocationWithZeroZero {
        private Location locZero;
        @BeforeEach
        void classSetup() {
            locZero = new Location("Zero", 0,0);
        }

            @Test
            void isOnEquatorZero() {
                assertTrue(locZero.isEquator());
            }


            @Test
            void isOnPrimeMeridian() {
                assertTrue(locZero.isOnPrimeMeridian());
            }
    }

    @Nested
    public class LocationBudapest {
        private Location locBud;
        @BeforeEach
        void classSetup() {
            locBud = new Location("Budapest", 47.497912,19.040235);
        }

        @Test
        void isOnEquatorBud() {
            assertFalse(locBud.isEquator());
        }

        @Test
        void isOnPrimeMeridian() {
            assertFalse(locBud.isEquator());
            assertFalse(locBud.isOnPrimeMeridian());
        }
    }
}
