import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaDelete;

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
        EntityManager em = factory.createEntityManager();

        List<Meetingroom> meetingrooms = em.createQuery("select m from Meetingroom m order by m.id").getResultList();

        em.close();

        return meetingrooms;

    }

     
    public List<Meetingroom> findMeetingroomByName(String name) {
        EntityManager em = factory.createEntityManager();

        List<Meetingroom> meetingrooms =em.createQuery("select m from Meetingroom m where m.name = :name")
                .setParameter("name",name)
                .getResultList();
        em.close();
        return meetingrooms;
    }

     
    public List<Meetingroom> findMeetingroomsByPrefix(String nameOrPrefix) {
        EntityManager em = factory.createEntityManager();

        List<Meetingroom> meetingrooms =em.createQuery("select m from Meetingroom m where m.name like :name")
                .setParameter("name",nameOrPrefix+"%")
                .getResultList();
        em.close();
        return meetingrooms;
    }

     
    public void deleteAll() {
        EntityManager em = factory.createEntityManager();
        em.createQuery("delete from Meetingroom m");
        em.close();
    }
}