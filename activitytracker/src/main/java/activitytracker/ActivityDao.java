package activitytracker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.time.LocalDateTime;
import java.util.List;

public class ActivityDao {
    private EntityManagerFactory entityManagerFactory;

    public ActivityDao(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void saveActivity( Activity activity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(activity);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void updateActivity(Activity activity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Activity activity1 = entityManager.find(Activity.class, activity.getId());

        activity1.setType(activity.getType());
        activity1.setStartTime(activity.getStartTime()) ;
        activity1.setDescription(activity.getDescription()) ;

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void deleteActivity(long id) {
        System.out.println(id);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Activity activity = entityManager.find(Activity.class,id);
        entityManager.remove(activity);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<Activity> listActivities() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Activity> activities = entityManager.createQuery("select a from Activity a order by a.description",Activity.class).getResultList();
        entityManager.close();
        return activities;
    }

    public Activity findActivityById(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Activity activity =  entityManager.find(Activity.class,id);
        entityManager.close();
        return activity;
    }

    public List<Activity> findTrackPointCoordinatesByDate(LocalDateTime afterThis) {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<Activity> coordinates = em.createQuery("Select a from Activity a  where a.startTime > :time", Activity.class)
                .setParameter("time", afterThis)
                .getResultList();
        em.close();
        return coordinates;
    }

}
