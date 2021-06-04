import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mariadb.jdbc.MariaDbDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MeetingRoomsServiceTestInDatabase {
    InDatabaseMeetingRoomsRepository repo;
    MeetingRoomsServices services;
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
            throw new IllegalStateException("Cannot create datatsource" , sqle);
        }

        jdbcTemplate = new JdbcTemplate(mariaDbDataSource);

    }


    @BeforeEach
    void initEach() {
        Flyway flyway = Flyway.configure().dataSource(mariaDbDataSource).load();
        flyway.clean();
        flyway.migrate();
        repo =  new InDatabaseMeetingRoomsRepository();
        services = new MeetingRoomsServices(repo);
        repo.save("Himalája", 5,5);
    }

    @Test
    void testSave() {

        List<String> result = services.getNames();

        assertEquals(List.of("Ábrahám","Benjámin","Himalája","Ménrót","Ráhel","Valéria"), result);
    }

    @Test
    void getNamesReversed() {

        List<String> result = services.getNamesReversed();


        assertEquals(List.of("Valéria","Ráhel","Ménrót","Himalája","Benjámin","Ábrahám"), result);
    }

    @Test
    void getNamesEven() {
        List<String> result = services.getNamesEven();

        assertEquals(List.of("Benjámin","Ménrót","Valéria"), result);
    }

    @Test
    void getAreas() {
        List<MeetingRoom> resultRooms = services.getAreas();

        assertEquals(
                List.of(
                        new MeetingRoom(2,"Ménrót",12,14,168),
                        new MeetingRoom(4,"Benjámin",9,9,81),
                        new MeetingRoom(1,"Ábrahám",7,11,77),
                        new MeetingRoom(3,"Valéria", 5,7,35),
                        new MeetingRoom(5,"Himalája",5, 5,25),
                        new MeetingRoom(3,"Ráhel",3,4,12)
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
        assertEquals("{név='Himalája', szélesség=5, Hosszúság=5, terület=25}" + System.lineSeparator(),result.get(0));
    }

    @Test
    void findGreater() {
        List<String> result = services.findGreater(80);
        assertEquals("{név='Benjámin', szélesség=9, Hosszúság=9, terület=81}" + System.lineSeparator(),result.get(0));
        assertEquals("{név='Ménrót', szélesség=12, Hosszúság=14, terület=168}" + System.lineSeparator(),result.get(1));
    }

}
