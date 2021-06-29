package bikeservice;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;


class BikeTest {

    @Test
    void parseFromCsv() {
        Bike parsed= Bike.parseFromCsv("FH675;US3434;2021-06-24 17:12:50;0.8");
        Bike constructed = new Bike("FH675","US3434", LocalDateTime.of(2021,06,24,17,12,50),0.8);

        assertThat(parsed).isEqualTo(constructed);
    }

}