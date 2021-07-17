package activitytracker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class ActivityTrackerMain {
    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        em.persist(new Activity(
                LocalDateTime.of(2020,10,18,8,0,0),
                "Kosarazás Marcival",
                ActivityType.BASKETBALL
                ));
        em.persist(new Activity(
                LocalDateTime.of(2020,10,19,8,0,0),
                "Bringázás Marcival",
                ActivityType.BIKING
                ));
        em.persist(new Activity(
                LocalDateTime.of(2020,10,20,8,0,0),
                "Kirándulás Marcival",
                ActivityType.HIKING
                ));

        em.getTransaction().commit();
    }
}
