package locations;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@NoArgsConstructor
public class LocationsService {

    private List<Location> locations = new ArrayList<>(
            List.of(
                    new Location(1L,"Budapest",43.112,19.227) ,
                    new Location(2L,"Pécs",43.112,19.227),
                    new Location(3L,"Pécs",43.112,19.227)
            )
    );

    public List<Location> getLocations() {
        return locations;
    }


}
