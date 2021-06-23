package locations;

import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DistanceServiceTest {


    @Mock
    LocationRepository repo;

    @InjectMocks
    DistanceService service;

    @Test
    void testCalculateDistanceInvalidLocation() {
        assertFalse(service.calculateDistance("Budapest","Bagdad").isPresent());
        verify(repo).findByName("Budapest");
        verify(repo).findByName("Bagdad");
    }

    @Test
    void testServiceFindByName() {

        Location bp = new Location("Budapest",47.49838,19.04051);
        Location deb = new Location("Debrecen",47.531693,21.626313);

        Optional<Location> obp = Optional.of(bp);
        Optional<Location> odeb = Optional.of(deb);

        when(repo.findByName("Budapest")).thenReturn(obp);
        when(repo.findByName("Debrecen")).thenReturn(odeb);

        Optional<Location> loc1 = service.findByName("Budapest");
        verify(repo).findByName("Budapest");

        Optional<Location> loc2 = service.findByName("Debrecen");
        verify(repo).findByName("Debrecen");

        assertTrue(loc1.isPresent());
        assertTrue(loc2.isPresent());

    }

    @Test
    void testServiceCalculateDistanceByMock() {
        when(repo.findByName("Budapest")).thenReturn(Optional.of(new Location("Budapest",47.49838,19.04051)));
        when(repo.findByName("Debrecen")).thenReturn(Optional.of(new Location("Debrecen",47.531693,21.626313)));

        Optional<Double> result = service.calculateDistance("Budapest","Debrecen");

        verify(repo).findByName("Budapest");
        verify(repo).findByName("Debrecen");

        assertTrue(result.isPresent());
        assertThat(result.get()).isCloseTo(194200, Percentage.withPercentage(0.05));
    }

}
