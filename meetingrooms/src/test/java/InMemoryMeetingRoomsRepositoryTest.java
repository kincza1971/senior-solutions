import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryMeetingRoomsRepositoryTest {

    InMemoryMeetingRoomsRepository repo;

    static MeetingRoom himalaja;
    static MeetingRoom everest;
    static MeetingRoom kekes;
    static MeetingRoom alpok;

    @BeforeAll
    static void beforeAll() {
        himalaja = new MeetingRoom("Himalája", 5,5);
        everest = new MeetingRoom("Mount Everest", 10,10);
        kekes = new MeetingRoom("Kékestető", 4,4);
        alpok = new MeetingRoom("Alpok",6,9);
    }

    @BeforeEach
    void init() {
        repo =  new InMemoryMeetingRoomsRepository();
        repo.save(himalaja);
        repo.save(everest);
        repo.save(kekes);
        repo.save(alpok);
  }

    @Test
    void testSave() {

        List<String> result = repo.getNames();
        assertEquals(List.of("Alpok","Himalája","Kékestető","Mount Everest"), result);
    }

    @Test
    void getNamesReversed() {
        List<String> result = repo.getNamesReversed();
        assertEquals(List.of("Mount Everest","Kékestető","Himalája","Alpok"), result);
    }

    @Test
    void getNamesEven() {
        List<String> result = repo.getNamesEven();
        assertEquals(List.of("Himalája","Mount Everest"), result);
    }

    @Test
    void getAreas() {
        List<MeetingRoom> resultRooms = repo.getAreas();
        assertEquals(List.of(everest,alpok, himalaja,kekes),resultRooms);
    }


    @Test
    void findByName() {
        Optional<MeetingRoom> result = repo.findByName("himaLÁJa");
        assertTrue(result.isPresent());
        result.ifPresent(meetingRoom -> assertEquals(himalaja, meetingRoom));
    }

    @Test
    void findByNamePart() {
        List<String> result = repo.findByNamePart("LÁJ");
        assertEquals("{név='Himalája', szélesség=5, Hosszúság=5, terület=25}" + System.lineSeparator(),result.get(0));
    }

    @Test
    void findGreater() {
        List<String> result = repo.findGreater(53);
        assertEquals(List.of(
                "{név='Alpok', szélesség=6, Hosszúság=9, terület=54}" + System.lineSeparator(),
                "{név='Mount Everest', szélesség=10, Hosszúság=10, terület=100}" + System.lineSeparator()),
                result);
    }
}