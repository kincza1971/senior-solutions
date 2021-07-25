package activitytracker;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class ActivityTrackerMain {
    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");
        ActivityDao activityDao = new ActivityDao(factory);
        activityDao.saveActivity(
                LocalDateTime.of(2020,10,18,8,0,0),
                "Kosarazás Marcival",
                ActivityType.BASKETBALL
                );
        activityDao.saveActivity(
                LocalDateTime.of(2020,10,19,8,0,0),
                "Bringázás Marcival",
                ActivityType.BIKING
                );
        activityDao.saveActivity(
                LocalDateTime.of(2020,10,20,8,0,0),
                "Kirándulás Marcival",
                ActivityType.HIKING
                );

        Activity a = new Activity(
                LocalDateTime.of(2020,10,21,8,0,0),
                "Kirándulás Marcival a hegyekbe",
                ActivityType.HIKING
        );
        a.setLabels(Set.of("Kilátás", "Kék túra"));
        a.setTrackPoints(List.of(
                new TrackPoint(43.1254,19.1225,a.getStartTime().plusMinutes(20)) ,
                new TrackPoint(43.1254,19.221,a.getStartTime().plusMinutes(50))
        ));
        activityDao.saveActivity(a);

        List<Activity> activities = activityDao.listActivities();
        System.out.println(activities);

        a = activityDao.findActivityByIdWithLabels(1L);
        Activity a1 = activityDao.findActivityByIdWithLabels(2L);

        System.out.println(a);

        activityDao.updateActivity(a1.getId(),"Kerékpározás a hegyekben Marcival");

        activityDao.deleteActivity(1L);

        a1.addLabel("Label1");
        a1.addLabel("Label2");

        activityDao.updateActivityByMerge(a1);

        activities =activityDao.listActivities();
        System.out.println(activities);
        a1=activityDao.findActivityByIdWithLabels(a1.getId());
        System.out.println(a1.getLabels());

        System.out.println(activityDao.findTrackPointCoordinatesByDate(LocalDateTime.of(2020,10,20,1,1,1)));
        System.out.println(activityDao.findActivityByIdWithTrackPoints(4L));

    }
}
