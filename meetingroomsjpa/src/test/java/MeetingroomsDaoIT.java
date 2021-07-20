import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class MeetingroomsDaoIT {
}


class MeetingRoomsRepositoryImplIT {

    MeetingroomsDao meetingrommsDao;

    @BeforeEach
    void setUp() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");
        meetingrommsDao = new MeetingroomsDao(factory);
    }

    @Test
    void saveAndRead() {
        meetingrommsDao.save("Alabama",10,122);
        meetingrommsDao.save("Montana",20,9);
        meetingrommsDao.save("Miami",40,30);
        List<String> result = meetingrommsDao.getMeetingroomsOrderedByName();

        assertEquals(3, result.size());
        assertEquals("Alabama",result.get(0));
    }


}