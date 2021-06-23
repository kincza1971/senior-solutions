package locations;

import java.util.Optional;

public class DistanceService {
    private LocationRepository repo;

    public DistanceService(LocationRepository repo) {
        this.repo = repo;
    }

    Optional<Location> findByName(String name) {
        return repo.findByName(name);
    }

    Optional<Double> calculateDistance(String name1, String name2) {
        Optional<Location> opt1 =  repo.findByName(name1);
        Optional<Location> opt2 =   repo.findByName(name2);
        if(opt1.isPresent() && opt2.isPresent()) {
            return Optional.of(opt1.get().distanceFrom(opt2.get()));
        }
        return Optional.empty();
    }
}
