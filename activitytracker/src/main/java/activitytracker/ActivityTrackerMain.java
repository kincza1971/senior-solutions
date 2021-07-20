package activitytracker;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class ActivityTrackerMain {
    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");
        ActivityDao activityDao = new ActivityDao(factory);
        activityDao.saveActivity(new Activity(
                LocalDateTime.of(2020,10,18,8,0,0),
                "Kosarazás Marcival",
                ActivityType.BASKETBALL
                ));
        activityDao.saveActivity(new Activity(
                LocalDateTime.of(2020,10,19,8,0,0),
                "Bringázás Marcival",
                ActivityType.BIKING
                ));
        activityDao.saveActivity(new Activity(
                LocalDateTime.of(2020,10,20,8,0,0),
                "Kirándulás Marcival",
                ActivityType.HIKING
                ));

        List<Activity> activities = activityDao.listActivities();
        System.out.println(activities);

        Activity a = activityDao.findActivityById(1L);
        Activity a1 = activityDao.findActivityById(2L);

        System.out.println(a);

        a1.setType(ActivityType.RUNNING);
        activityDao.updateActivity(a1);

        activityDao.deleteActivity(1L);

        activities =activityDao.listActivities();
        System.out.println(activities);

        System.out.println(activityDao.findTrackPointCoordinatesByDate(LocalDateTime.of(2020,10,20,1,1,1)));

    }
}
