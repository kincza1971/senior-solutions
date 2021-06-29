package bikeservice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Bike {
    private final String bikeId;
    private final String userId;
    private final LocalDateTime lastUsedTime;
    private final double lastDistance;

    public Bike(String bikeId, String userId, LocalDateTime lastUsedTime, double lastDistance) {
        this.bikeId = bikeId;
        this.userId = userId;
        this.lastUsedTime = lastUsedTime;
        this.lastDistance = lastDistance;
    }

    public String getBikeId() {
        return bikeId;
    }

    public String getUserId() {
        return userId;
    }

    public LocalDateTime getLastUsedTime() {
        return lastUsedTime;
    }

    public double getLastDistance() {
        return lastDistance;
    }

    public static Bike parseFromCsv(String csv) {
        String[] parts = csv.split(";");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return new Bike(
                parts[0],
                parts[1],
                LocalDateTime.parse(parts[2],formatter),
                Double.parseDouble(parts[3])
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bike)) return false;
        Bike bike = (Bike) o;
        return Double.compare(bike.getLastDistance(), getLastDistance()) == 0
                && getBikeId().equals(bike.getBikeId())
                && getUserId().equals(bike.getUserId())
                && getLastUsedTime().equals(bike.lastUsedTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBikeId(), getUserId(), getLastUsedTime(), getLastDistance());
    }

    @Override
    public String toString() {
        return "Bike{" +
                "bikeId='" + bikeId + '\'' +
                ", userId='" + userId + '\'' +
                ", lastUsedTime=" + lastUsedTime +
                ", lastDistance=" + lastDistance +
                '}';
    }
}

//* A bicikli azonosítója
//* Az utolsó felhasználó egyedi azonosítója
//* Az utolsó leadás pontos ideje
//* Az utolsó úton megtett távolság kilometerben
