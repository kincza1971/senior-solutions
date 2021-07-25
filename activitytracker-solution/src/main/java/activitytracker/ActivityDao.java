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

    public void saveActivity( LocalDateTime startTime, String description, ActivityType type) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(new Activity(startTime,description,type));
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void saveActivity(Activity activity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(activity);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void updateActivity( long id, String description) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Activity activity1 = entityManager.find(Activity.class, id);

//        activity1.setType(activity.getType());
//        activity1.setStartTime(activity.getStartTime()) ;
        activity1.setDescription(description) ;

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

    public Activity findActivityByIdWithLabels(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Activity activity =  entityManager
                .createQuery("select a from Activity a left join fetch a.labels where a.id = :id",Activity.class)
                .setParameter("id",id)
                .getSingleResult();
        entityManager.close();
        return activity;
    }

    public Activity findActivityByIdWithTrackPoints(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Activity activity =  entityManager
                .createQuery("select a from Activity a left join fetch a.trackPoints where a.id = :id order by a.id",Activity.class)
                .setParameter("id",id)
                .getSingleResult();
        entityManager.close();
        return activity;
    }

    public List<Activity> findTrackPointCoordinatesByDate(LocalDateTime afterThis) {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<Activity> coordinates = em.createQuery("Select a from Activity a where a.startTime > :time", Activity.class)
                .setParameter("time", afterThis)
                .getResultList();
        em.close();
        return coordinates;
    }

    public Activity updateActivityByMerge(Activity changedActivity) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(changedActivity);
        em.getTransaction().commit();
        em.close();
        return findActivityById(changedActivity.getId());
    }

}
