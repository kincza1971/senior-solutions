package bikeservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BikeServiceTest {
    BikeService service;

    @BeforeEach
    void init() {
        service = new BikeService();
    }

    @Test
    void getBikes() {
        List<String> result = service.getBikes().stream().map(Bike::toString).toList();

        List<String> expected = List.of(
                "Bike{bikeId='FH675', userId='US3434', lastUsedTime=2021-06-24T17:12:50, lastDistance=0.8}",
                "Bike{bikeId='FH676', userId='US3a34', lastUsedTime=2021-06-25T11:20:42, lastDistance=1.2}",
                "Bike{bikeId='FH676', userId='US3334', lastUsedTime=2021-06-25T12:40:37, lastDistance=0.7}",
                "Bike{bikeId='FH636', userId='US336', lastUsedTime=2021-06-23T09:36:12, lastDistance=1.9}",
                "Bike{bikeId='FH631', userId='US346', lastUsedTime=2021-06-24T08:53:21, lastDistance=2.9}");

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void getUsers() {
        List<String>result = service.getUsers();

        List<String> expected = List.of("US3434", "US3a34", "US3334", "US336", "US346");

        assertThat(result).isEqualTo(expected);
    }
}