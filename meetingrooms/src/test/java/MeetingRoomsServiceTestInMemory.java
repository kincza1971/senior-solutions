import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MeetingRoomsServiceTestInMemory {
    InMemoryMeetingRoomsRepository repo;
    MeetingRoomsServices services;

    @BeforeEach
    void init() {
        repo = new InMemoryMeetingRoomsRepository();
        services = new MeetingRoomsServices(repo);
        services.save("Himalája", 5,5);
        services.save("Mount Everest", 10,10);
        services.save("Kékestető", 4,4);
        services.save("Alpok",6,9);
    }

    @Test
    void testSave() {

        List<String> result = services.getNames();

        assertEquals(List.of("Alpok", "Himalája","Kékestető","Mount Everest"),result);
    }

    @Test
    void getNamesReversed() {

        List<String> result = services.getNamesReversed();

        assertEquals(List.of("Mount Everest","Kékestető","Himalája","Alpok" ),result);
    }

    @Test
    void getNamesEven() {
        List<String> result = services.getNamesEven();

        assertEquals(List.of("Himalája","Mount Everest"),result);
    }

    @Test
    void getAreas() {
        List<MeetingRoom> resultRooms = services.getAreas();

        assertEquals(
                List.of(
                    new MeetingRoom(2,"Mount Everest",10,10,100),
                    new MeetingRoom(4,"Alpok",6,9,54) ,
                    new MeetingRoom(1,"Himalája",5,5,25),
                    new MeetingRoom(3,"Kékestető",4,4,16)
                ),
                resultRooms
        );
    }


    @Test
    void findByName() {
        Optional<MeetingRoom> result = repo.findByName("himaLÁJa");
        assertTrue(result.isPresent());
        result.ifPresent(meetingRoom -> assertEquals(new MeetingRoom(1, "Himalája", 5, 5, 25), meetingRoom));
    }

    @Test
    void findByNamePart() {
        List<String> result = services.findByNamePart("LÁJ");
        System.out.println(result);
        assertEquals("{név='Himalája', szélesség=5, Hosszúság=5, terület=25}" + System.lineSeparator(),result.get(0));
        assertEquals(1,result.size());
    }

    @Test
    void findGreater() {
        List<String> result = services.findGreater(53);
        assertEquals(
                List.of(
                        "{név='Alpok', szélesség=6, Hosszúság=9, terület=54}" + System.lineSeparator(),
                        "{név='Mount Everest', szélesség=10, Hosszúság=10, terület=100}" + System.lineSeparator()
                ),
                result
        );
    }
}