package locations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/locations")
public class LocationsController {

    private LocationsService locationsService;

    public LocationsController(LocationsService locationsService) {
        this.locationsService = locationsService;
    }

    @GetMapping
    public List<LocationDTO> getLocations(@RequestParam Optional<String> prefix) {

        return locationsService.getLocations(prefix);
    }

    @GetMapping("/{id}")
    public LocationDTO findLocationById(@PathVariable long id) {
        return locationsService.findLocationById(id);
    }

    @PostMapping()
    public LocationDTO createLocation(@RequestBody CreateCommand command) {
        return locationsService.createLocation(command);
    }

    @PutMapping("/{id)")
    public LocationDTO updateLocation(@PathVariable long id, @RequestBody UpdateLocationCommand command) {
        return locationsService.updateLocationById(id, command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLocationById(@PathVariable long id) {
        locationsService.deleteLocationById(id);
    }

}
