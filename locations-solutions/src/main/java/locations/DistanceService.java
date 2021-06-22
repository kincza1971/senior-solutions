package locations;

import java.util.Optional;

public class DistanceService {
    private LocationRepository repo;

    public DistanceService(LocationRepository repo) {
        this.repo = repo;
    }

    Optional<Double> calculateDistance(String name1, String name2) {

        Optional<Location> location1 = repo.findByName(name1);
        if (!location1.isPresent()) {
            return Optional.empty();
        }

        Optional<Location> location2 = repo.findByName(name1);
        if (!location2.isPresent()) {
            return Optional.empty();
        }

        return Optional.of(location1.get().distanceFrom(location2.get()));
    }
}
