package moviesspring;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicLong;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MovieTest {
    static Movie m1;
    static Movie m2;
    static Movie m3;
    static Movie m4;

    @BeforeAll
    static void init() {
        m1 =new Movie(1L,"Titanic",123);
        m2 =new Movie(2L,"Alien",127);

    }

    @Test
    void addRate() {
        m1.addRate(5);
        m1.addRate(4);
        m1.addRate(3);
        assertThat(m1.getAvgRate()).isEqualTo(4.0);
        assertThat(m2.getAvgRate()).isEqualTo(0.0);
    }
}