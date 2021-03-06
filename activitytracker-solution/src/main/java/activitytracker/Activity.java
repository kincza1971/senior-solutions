package activitytracker;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="activities")
public class Activity {
    public static final String ID_GEN = "ACTIVITY_GEN";

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = ID_GEN)
    @TableGenerator(name = ID_GEN,table = "act_id_gen",pkColumnName = "id_gen" ,pkColumnValue = "akt_id_val",allocationSize = 10,valueColumnName = "id_val")
    @Column(name = "act_id")
    private long id;
    private LocalDateTime startTime;
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(length = 15, nullable = false)
    private ActivityType type;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

//    @ElementCollection(fetch = FetchType.EAGER)
    @ElementCollection
    @CollectionTable(name = "activity_labels", joinColumns = @JoinColumn(name = "act_id"))
    @Column(name = "label")

    private Set<String> labels = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "trackpoints", joinColumns = @JoinColumn(name = "act_id"))
    @Column(name = "trackpoint")
    private List<TrackPoint> trackPoints = new ArrayList<>();

    public Activity() {
    }

    public Activity(LocalDateTime startTime, String description, ActivityType type) {
        this.startTime = startTime;
        this.description = description;
        this.type = type;
    }

    public void addLabel(String label) {
        labels.add(label);
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Set<String> getLabels() {
        return labels;
    }

    public void setLabels(Set<String> labels) {
        this.labels = labels;
    }

    public List<TrackPoint> getTrackPoints() {
        return trackPoints;
    }

    public void setTrackPoints(List<TrackPoint> trackPoints) {
        this.trackPoints = trackPoints;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", startTime=" + startTime +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }


    @PrePersist
    private void setCreated() {
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
    }
    @PreUpdate
    private void SetUpdated() {
        updatedAt = LocalDateTime.now();
    }
}


//??rj egy activitytracker.Activity oszt??lyt, mely attrib??tumai:
//
//id - egyedi azonos??t??, eg??sz sz??m
//startTime - kezd??si id??, LocalDateTime
//description - sz??veges le??r??s (vigy??zz, a desc foglalt sz?? SQL-ben)
//type - felsorol??sos t??pus, mely felveheti a k??vetkez?? ??rt??keket: BIKING, HIKING, RUNNING, BASKETBALL
//??rj egy activitytracker.ActivityTrackerMain oszt??lyt, mely egy main() met??dusban, mely p??ld??nyos??t p??r Activity p??ld??nyt, ??s ??rt??k??ket besz??rja az activities t??bl??ba! Az adatb??zisban hozd l??tre az activities t??bl??t! Az id legyen auto_generated! A mez??nevek legyenek hasonl??ak az oszt??ly attrib??tumainak neveihez, azonban a szavakat al??h??z??sjel (_) v??lassza el!
//
//Amennyiben k??sz, szervezd ki a besz??r??st egy k??l??n met??dusba!