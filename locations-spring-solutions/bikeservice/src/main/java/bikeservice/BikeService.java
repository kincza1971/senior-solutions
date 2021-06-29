package bikeservice;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


@Service
public class BikeService {

    Path file = Path.of("bikes.csv");

    List<Bike> bikes = new ArrayList<>();

    public List<Bike> getBikes() {
        if (bikes.isEmpty()) {
            loadBikesFromFile();
        }
        return bikes;
    }

    private void loadBikesFromFile() {
        try {
            bikes = Files.readAllLines(file)
                    .stream()
                    .map(Bike::parseFromCsv)
                    .toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getUsers() {
        if (bikes.isEmpty()) {
            loadBikesFromFile();
        }
        return bikes
                .stream()
                .map(Bike::getUserId)
                .toList();
    }

}
