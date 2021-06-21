package locations;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class LocationService {
    public void writeLocations(Path file, List<Location> locations) {
        try (BufferedWriter bw = Files.newBufferedWriter(file)){
            for (Location l : locations) {
                bw.write(l.toCsv() + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Location> loadLocations(Path file) {
        LocationParser locationParser = new LocationParser();
        LocationOperators locationOperators = new LocationOperators();
        try {
            locationOperators.addLocations(
                            Files.lines(file)
                                    .map(locationParser::parse)
                                    .toList()
                    );
            return locationOperators.getLocations();
        } catch (IOException e) {
            throw new IllegalStateException("Cannot read or process file ",e);
        }
    }
}
