package moviesspring;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MovieServiceTest {

    MovieService movieService;
    ModelMapper modelMapper;


    MovieDto m1;
    MovieDto m2;
    MovieDto m3;
    MovieDto m4;

    @BeforeEach
    void init() {
        modelMapper = new ModelMapper();
        movieService = new MovieService(modelMapper);
        m1 =new MovieDto("Titanic",123,0.0);
        m2 =new MovieDto("Alien",127,0.0);
        m3 =new MovieDto("Becstelen Brigantik",119,0.0);
        m4 =new MovieDto("Ponyvaregény",117,0.0);

        movieService.addMovie(new CreateMovieCommand("Titanic",123));
        movieService.addMovie(new CreateMovieCommand("Alien",127));
        movieService.addMovie(new CreateMovieCommand("Becstelen Brigantik",119));
        movieService.addMovie(new CreateMovieCommand("Ponyvaregény",117));

    }


    @Test
    void listMovies() {
        assertThat(movieService.listMovies()).isEqualTo(List.of(m1,m2,m3,m4));
    }

    @Test
    void findMovieById() {
        assertThat(movieService.findMovieById(1)).isEqualTo(m1);
        assertThat(movieService.findMovieById(4)).isEqualTo(m4);
    }


    @Test
    void deleteMovie() {
        movieService.deleteMovie(1L);
        movieService.deleteMovie(3L);
        assertThat(movieService.listMovies()).isEqualTo(List.of(m2,m4));
    }

    @Test
    void addRate() {

        movieService.addRate(1,new RateCommand(5));
        movieService.addRate(1,new RateCommand(4));
        movieService.addRate(1,new RateCommand(3));
        assertThat(movieService.findMovieById(1).getAvgRate()).isEqualTo(4.0);
    }

}