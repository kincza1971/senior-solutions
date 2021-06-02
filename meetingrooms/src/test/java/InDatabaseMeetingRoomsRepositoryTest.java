import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mariadb.jdbc.MariaDbDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InDatabaseMeetingRoomsRepositoryTest {

    InDatabaseMeetingRoomsRepository repo;
    private JdbcTemplate jdbcTemplate;


    @BeforeEach
    void init() {
        try {
            MariaDbDataSource mariaDbDataSource = new MariaDbDataSource();
            mariaDbDataSource.setUrl("jdbc:mariadb://localhost:3306/meetingrooms?useUnicode=true");
            mariaDbDataSource.setUser("meetingroomuser");
            mariaDbDataSource.setPassword("user");

            jdbcTemplate = new JdbcTemplate(mariaDbDataSource);

            Flyway flyway = Flyway.configure().dataSource(mariaDbDataSource).load();
            flyway.clean();
            flyway.migrate();

        } catch (SQLException sqle) {
            throw new IllegalStateException("Cannot create datasource");
        }

        repo =  new InDatabaseMeetingRoomsRepository();
        repo.save("Himalája", 5,5);
    }

    @Test
    void testSave() {

        List<String> result = repo.getNames();
        System.out.println(result);

        assertEquals("Ábrahám", result.get(0));
        assertEquals("Benjámin", result.get(1));
        assertEquals("Himalája", result.get(2));
        assertEquals("Ménrót", result.get(3));
        assertEquals("Ráhel", result.get(4));
        assertEquals("Valéria", result.get(5));
    }

    @Test
    void getNamesReversed() {

        List<String> result = repo.getNamesReversed();

        assertEquals("Ábrahám", result.get(5));
        assertEquals("Benjámin", result.get(4));
        assertEquals("Himalája", result.get(3));
        assertEquals("Ménrót", result.get(2));
        assertEquals("Ráhel", result.get(1));
        assertEquals("Valéria", result.get(0));
    }

    @Test
    void getNamesEven() {
        List<String> result = repo.getNamesEven();

        assertEquals("Benjámin", result.get(0));
        assertEquals("Ménrót", result.get(1));
        assertEquals("Valéria", result.get(2));
    }

    @Test
    void getAreas() {
        List<MeetingRoom> resultRooms = repo.getAreas();

        assertEquals(new MeetingRoom(2,"Ménrót",12,14,168),resultRooms.get(0));
        assertEquals(new MeetingRoom(4,"Benjámin",9,9,81),resultRooms.get(1));
        assertEquals(new MeetingRoom(1,"Ábrahám",7,11,77),resultRooms.get(2));
        assertEquals(new MeetingRoom(3,"Ráhel",3,4,12),resultRooms.get(5));

    }


    @Test
    void findByName() {
        MeetingRoom result = repo.findByName("himaLÁJa");
        assertEquals(new MeetingRoom(1,"Himalája",5,5,25),result);
    }

    @Test
    void findByNamePart() {
        List<String> result = repo.findByNamePart("LÁJ");
        System.out.println(result);
        assertEquals("{név='Himalája', szélesség=5, Hosszúság=5, terület=25}" + System.lineSeparator(),result.get(0));
    }

    @Test
    void findGreater() {
        List<String> result = repo.findGreater(80);
        assertEquals("{név='Benjámin', szélesség=9, Hosszúság=9, terület=81}" + System.lineSeparator(),result.get(0));
        assertEquals("{név='Ménrót', szélesség=12, Hosszúság=14, terület=168}" + System.lineSeparator(),result.get(1));
    }
}