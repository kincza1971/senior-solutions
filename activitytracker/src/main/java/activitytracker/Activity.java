package activitytracker;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="activities")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDateTime startTime;
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(length = 15, nullable = false)
    private ActivityType type;

    public Activity() {
    }

    public Activity(LocalDateTime startTime, String description, ActivityType type) {
        this.startTime = startTime;
        this.description = description;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ActivityType getType() {
        return type;
    }

    public void setType(ActivityType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", startTime=" + startTime +
                ", description='" + description + '\'' +
                ", type=" + type +
                '}';
    }
}


//Írj egy activitytracker.Activity osztályt, mely attribútumai:
//
//id - egyedi azonosító, egész szám
//startTime - kezdési idő, LocalDateTime
//description - szöveges leírás (vigyázz, a desc foglalt szó SQL-ben)
//type - felsorolásos típus, mely felveheti a következő értékeket: BIKING, HIKING, RUNNING, BASKETBALL
//Írj egy activitytracker.ActivityTrackerMain osztályt, mely egy main() metódusban, mely példányosít pár Activity példányt, és értéküket beszúrja az activities táblába! Az adatbázisban hozd létre az activities táblát! Az id legyen auto_generated! A mezőnevek legyenek hasonlóak az osztály attribútumainak neveihez, azonban a szavakat aláhúzásjel (_) válassza el!
//
//Amennyiben kész, szervezd ki a beszúrást egy külön metódusba!