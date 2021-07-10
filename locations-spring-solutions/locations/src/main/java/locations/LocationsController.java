package locations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/locations")
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
    @ResponseStatus(HttpStatus.CREATED)
    public LocationDTO createLocation(@RequestBody CreateCommand command) {
        return locationsService.createLocation(command);
    }

    @PutMapping("/{id)")
    public LocationDTO updateLocation(@PathVariable("id") long id, @RequestBody UpdateLocationCommand command) {
        return locationsService.updateLocationById(id, command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLocationById(@PathVariable("id") long id) {
        locationsService.deleteLocationById(id);
    }

    @ExceptionHandler(LocationNotFoundException.class)
    public ResponseEntity<Problem> notFoundHandler(LocationNotFoundException e) {
        Problem problem = Problem
                .builder()
                .withType(URI.create("locations/location-not-found"))
                .withTitle("Not Found")
                .withStatus(Status.NOT_FOUND)
                .withDetail(e.getMessage())
                .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }

}
