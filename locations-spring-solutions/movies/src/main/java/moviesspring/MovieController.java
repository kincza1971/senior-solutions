package moviesspring;


import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/movies")
public class MovieController {

    MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @RequestMapping
    public List<MovieDto> listMovies() {
        return movieService.listMovies();
    }

    @RequestMapping("/{id}")
    public MovieDto findMovieById(@PathVariable long id) {
        return movieService.findMovieById(id);
    }

    @PostMapping
    public MovieDto addMovie(@RequestBody CreateMovieCommand command) {
        return movieService.addMovie(command);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Long id) {
       movieService.deleteMovie(id);
    }
    @PostMapping("/{id}/Rate")
    public void addRate(@PathVariable long id, @RequestBody RateCommand command) {
        movieService.addRate(id, command);
    }


}
