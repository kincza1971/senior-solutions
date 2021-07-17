package locations;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class LocationsService {

    private ModelMapper modelMapper;

    private AtomicLong idGenerator = new AtomicLong();

    private List<Location> locations = new ArrayList<>(
//            List.of(
//                    new Location(idGenerator.incrementAndGet(),"Budapest",43.112,19.227) ,
//                    new Location(idGenerator.incrementAndGet(),"Pécs",43.112,19.227)
//            )
    );

    private LocationsDao locationsDao;


    public LocationsService(ModelMapper modelMapper, LocationsDao locationsDao) {
        this.modelMapper = modelMapper;
        this.locationsDao = locationsDao;
    }

    public List<LocationDTO> getLocations(Optional<String> prefix) {

        return locationsDao.findAllLocations().stream()
                .map(l -> modelMapper.map(l,LocationDTO.class))
                .toList();

//        return locations.stream()
//                .filter(l -> prefix.isEmpty() || l.getName().equalsIgnoreCase(prefix.get()))
//                .map(l -> modelMapper.map(l,LocationDTO.class))
//                .toList();
    }


    public LocationDTO findLocationById(Long id) {
        Location found = findById(id);
        return modelMapper.map(found,LocationDTO.class);
    }

    private Location findById(Long id) {
        return locations.stream()
                .filter(l -> l.getId()==id)
                .findAny()
                .orElseThrow(() -> new LocationNotFoundException("Cannot find location with this id: " +id));
    }

    public LocationDTO createLocation(CreateCommand command) {
        Location location = new Location(
                idGenerator.incrementAndGet(),
                command.getName(),
                command.getLat(),
                command.getLon()
        );
        locations.add(location);
        return modelMapper.map(location, LocationDTO.class);
    }

    public LocationDTO updateLocationById(long id, UpdateLocationCommand command) {
        Location location = findById(id);
        location.setName(command.getName());
        location.setLon(command.getLon());
        location.setLat(command.getLat());
        return modelMapper.map(location, LocationDTO.class);
    }

    public void deleteLocationById(long id) {
        Location location = findById(id);
        locations.remove(location);
    }

    public void deleteAllLocation() {
        idGenerator = new AtomicLong();
        locations.clear();
    }
}
