import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mariadb.jdbc.MariaDbDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class InDatabaseMeetingRoomsRepositoryTest {

    InDatabaseMeetingRoomsRepository repo;
    static JdbcTemplate jdbcTemplate;
    static MariaDbDataSource mariaDbDataSource;


    @BeforeAll
    static void init() {
        try {
            mariaDbDataSource = new MariaDbDataSource();
            mariaDbDataSource.setUrl("jdbc:mariadb://localhost:3306/meetingrooms?useUnicode=true");
            mariaDbDataSource.setUser("meetingroomuser");
            mariaDbDataSource.setPassword("user");
        } catch (SQLException sqle) {
            throw new IllegalStateException("Cannot create datasource", sqle);
        }

        jdbcTemplate = new JdbcTemplate(mariaDbDataSource);

    }


    @BeforeEach
    void initEach() {
        Flyway flyway = Flyway.configure().dataSource(mariaDbDataSource).load();
        flyway.clean();
        flyway.migrate();
        repo =  new InDatabaseMeetingRoomsRepository();
        repo.save(new MeetingRoom("Himalája", 5,5));
    }

    @Test
    @Disabled
    void testSave() {

        List<String> result = repo.getNames();

        assertEquals(List.of("Ábrahám","Benjámin","Himalája","Ménrót","Ráhel","Valéria"), result);
    }

    @Test
    void testSaveWithMeetings() {
        MeetingRoom mr = new MeetingRoom("RoomWithMeetings",5,5);
        Meeting m1 = new Meeting("Én és én meg az Irén",
                LocalDateTime.of(2021,6,9,10,0),
                LocalDateTime.of(2021,6,9,11,0));
        Meeting m2 = new Meeting("Géza bá",
                LocalDateTime.of(2021,6,9,11,0),
                LocalDateTime.of(2021,6,9,12,0));
        Meeting m3 = new Meeting("Jolánka",
                LocalDateTime.of(2021,6,9,12,0),
                LocalDateTime.of(2021,6,9,13,0));
        mr.addMeetingIfFree(m1);
        mr.addMeetingIfFree(m2);
        mr.addMeetingIfFree(m3);

        repo.save(mr);

        mr = new MeetingRoom("Favorite 30",5,5);
        m1 = new Meeting("Sales Manager",
                LocalDateTime.of(2021,6,9,10,0),
                LocalDateTime.of(2021,6,9,11,0));
        m2 = new Meeting("Finance Manager",
                LocalDateTime.of(2021,6,9,11,0),
                LocalDateTime.of(2021,6,9,12,0));
        m3 = new Meeting("Developer Team",
                LocalDateTime.of(2021,6,9,12,0),
                LocalDateTime.of(2021,6,9,13,0));
        mr.addMeetingIfFree(m1);
        mr.addMeetingIfFree(m2);
        mr.addMeetingIfFree(m3);

        repo.save(mr);

        System.out.println(repo.getMeetings());

    }

    @Test
    @Disabled
    void getNamesReversed() {

        List<String> result = repo.getNamesReversed();


        assertEquals(List.of("Valéria","Ráhel","Ménrót","Himalája","Benjámin","Ábrahám"), result);
    }

    @Test
    @Disabled
    void getNamesEven() {
        List<String> result = repo.getNamesEven();

        assertEquals(List.of("Benjámin","Ménrót","Valéria"), result);
    }

    @Test
      @Disabled
    void getAreas() {
        List<MeetingRoom> resultRooms = repo.getAreas();

        assertEquals(new MeetingRoom(2,"Ménrót",12,14,168),resultRooms.get(0));
        assertEquals(new MeetingRoom(4,"Benjámin",9,9,81),resultRooms.get(1));
        assertEquals(new MeetingRoom(1,"Ábrahám",7,11,77),resultRooms.get(2));
        assertEquals(new MeetingRoom(3,"Ráhel",3,4,12),resultRooms.get(5));

    }


    @Test
    @Disabled
    void findByName() {
        Optional<MeetingRoom> result = repo.findByName("himaLÁJa");
        assertTrue(result.isPresent());
        result.ifPresent(meetingRoom -> assertEquals(new MeetingRoom(1, "Himalája", 5, 5, 25), meetingRoom));
    }

    @Test
    @Disabled
    void findByNamePart() {
        List<String> result = repo.findByNamePart("LÁJ");
        assertEquals("{név='Himalája', szélesség=5, Hosszúság=5, terület=25}" + System.lineSeparator(),result.get(0));
    }

    @Test
    @Disabled
    void findGreater() {
        List<String> result = repo.findGreater(80);
        assertEquals("{név='Benjámin', szélesség=9, Hosszúság=9, terület=81}" + System.lineSeparator(),result.get(0));
        assertEquals("{név='Ménrót', szélesség=12, Hosszúság=14, terület=168}" + System.lineSeparator(),result.get(1));
    }

}
