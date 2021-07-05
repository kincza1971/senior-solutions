package locations;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class LocationsService {
    private ModelMapper modelMapper;

    private List<Location> locations = new ArrayList<>(
            List.of(
                    new Location(1L,"Budapest",43.112,19.227) ,
                    new Location(3L,"Pécs",43.112,19.227)
            )
    );

    public LocationsService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<LocationDTO> getLocations() {

        return locations.stream()
                .map(l -> modelMapper.map(l,LocationDTO.class))
                .toList();
    }


}
