package locations;

import org.springframework.beans.factory.annotation.Autowired;
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
    public LocationDTO findLocationById(@PathVariable Long id) {
        return locationsService.findLocationById(id);
    }

}
