package bikeservice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BikeserviceApplicationTests {

    @Autowired
    BikeController bikeController;


    @Test
    @DisplayName("Integration test bikes")
    void contextLoads() {

        List<String> result = bikeController.getBikes().stream().map(Bike::toString).toList();

        List<String> expected = List.of(
                "Bike{bikeId='FH675', userId='US3434', lastUsedTime=2021-06-24T17:12:50, lastDistance=0.8}",
                "Bike{bikeId='FH676', userId='US3a34', lastUsedTime=2021-06-25T11:20:42, lastDistance=1.2}",
                "Bike{bikeId='FH676', userId='US3334', lastUsedTime=2021-06-25T12:40:37, lastDistance=0.7}",
                "Bike{bikeId='FH636', userId='US336', lastUsedTime=2021-06-23T09:36:12, lastDistance=1.9}",
                "Bike{bikeId='FH631', userId='US346', lastUsedTime=2021-06-24T08:53:21, lastDistance=2.9}");

        assertThat(result).isEqualTo(expected);

    }

}
