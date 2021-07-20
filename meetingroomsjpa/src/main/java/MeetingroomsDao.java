import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

import javax.persistence.EntityManagerFactory;

@RequiredArgsConstructor
public class MeetingroomsDao {
    private EntityManagerFactory factory;


    private final EntityManagerFactory entityManagerFactory;

     
    public Meetingroom save(String name, int width, int length) {
        Meetingroom Meetingroom = new Meetingroom(name, width, length);
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(Meetingroom);
        em.getTransaction().commit();
        em.close();
        return Meetingroom;
    }

     
    public List<String> getMeetingroomsOrderedByName() {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<String> roomsName = em.createQuery("select m.name from Meetingroom m order by m.name",String.class)
                .getResultList();
        em.close();
        return roomsName;
    }

     
    public List<String> getEverySecondMeetingroom() {
        return null;
    }

     
    public List<Meetingroom> getMeetingrooms() {
        return null;
    }

     
    public List<Meetingroom> getExactMeetingroomByName(String name) {
        return null;
    }

     
    public List<Meetingroom> getMeetingroomsByPrefix(String nameOrPrefix) {
        return null;
    }

     
    public void deleteAll() {

    }
}