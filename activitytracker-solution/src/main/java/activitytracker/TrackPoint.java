package activitytracker;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
public class TrackPoint {
    private double lat;
    private double lon;
    private LocalDateTime visitedAt;

    public TrackPoint() {
    }

    public TrackPoint(double lat, double lon, LocalDateTime visitedAt) {
        this.lat = lat;
        this.lon = lon;
        this.visitedAt = visitedAt;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public LocalDateTime getVisitedAt() {
        return visitedAt;
    }

    public void setVisitedAt(LocalDateTime visitedAt) {
        this.visitedAt = visitedAt;
    }
}
