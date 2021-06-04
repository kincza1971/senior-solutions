import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryMeetingRoomsRepositoryTest {

    InMemoryMeetingRoomsRepository repo;

    @BeforeEach
    void init() {
        repo =  new InMemoryMeetingRoomsRepository();
        repo.save("Himalája", 5,5);
        repo.save("Mount Everest", 10,10);
        repo.save("Kékestető", 4,4);
        repo.save("Alpok",6,9);
  }

    @Test
    void testSave() {

        List<String> result = repo.getNames();

        assertEquals("Alpok", result.get(0));
        assertEquals("Himalája", result.get(1));
        assertEquals("Kékestető", result.get(2));
        assertEquals("Mount Everest", result.get(3));
    }

    @Test
    void getNamesReversed() {

        List<String> result = repo.getNamesReversed();

        assertEquals("Alpok", result.get(3));
        assertEquals("Himalája", result.get(2));
        assertEquals("Kékestető", result.get(1));
        assertEquals("Mount Everest", result.get(0));
    }

    @Test
    void getNamesEven() {
        List<String> result = repo.getNamesEven();

        assertEquals("Himalája", result.get(0));
        assertEquals("Mount Everest", result.get(1));
    }

    @Test
    void getAreas() {
        List<MeetingRoom> resultRooms = repo.getAreas();

        assertEquals(new MeetingRoom(2,"Mount Everest",10,10,100),resultRooms.get(0));
        assertEquals(new MeetingRoom(4,"Alpok",6,9,54),resultRooms.get(1));
        assertEquals(new MeetingRoom(1,"Himalája",5,5,25),resultRooms.get(2));
        assertEquals(new MeetingRoom(3,"Kékestető",4,4,16),resultRooms.get(3));
    }


    @Test
    void findByName() {
        Optional<MeetingRoom> result = repo.findByName("himaLÁJa");
        assertTrue(result.isPresent());
        result.ifPresent(meetingRoom -> assertEquals(new MeetingRoom(1, "Himalája", 5, 5, 25), meetingRoom));
    }

    @Test
    void findByNamePart() {
        List<String> result = repo.findByNamePart("LÁJ");
        System.out.println(result);
        assertEquals("{név='Himalája', szélesség=5, Hosszúság=5, terület=25}" + System.lineSeparator(),result.get(0));
    }

    @Test
    void findGreater() {
        List<String> result = repo.findGreater(53);
        assertEquals("{név='Alpok', szélesség=6, Hosszúság=9, terület=54}" + System.lineSeparator(),result.get(0));
        assertEquals("{név='Mount Everest', szélesség=10, Hosszúság=10, terület=100}" + System.lineSeparator(),result.get(1));
    }
}