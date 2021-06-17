package locations;

import java.util.ArrayList;
import java.util.List;

public class LocationOperators {
    List<Location> locations = new ArrayList<>();

    public LocationOperators() {
    }

    public LocationOperators(List<Location> locations) {
        this.locations = locations;
    }

    public List<Location> getLocations() {
        return List.copyOf(locations);
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public List<Location> filterOnNorth() {
        return locations.stream()
                .filter(l -> l.getLat()>0)
                .toList();
    }

    public void addLocation(Location loc) {
        if (loc != null) {
            locations.add(loc);
        }
    }

    public void addLocations(List<Location> locs) {
        if (locs != null  && !locs.isEmpty()) {
            locations.addAll(locs);
        }
    }
}
