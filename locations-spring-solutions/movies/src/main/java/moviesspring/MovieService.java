package moviesspring;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class MovieService {

    private ModelMapper modelMapper;

    private List<Movie> movies = new ArrayList<>();

    private AtomicLong idGenerator = new AtomicLong();

    public MovieService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


//    public List<Movie> listMovies() {
//        //Type targetListType = new TypeToken<List<MovieDto>>(){}.getType();
//        return movies;
//    }



    public List<MovieDto> listMovies() {
        Type targetListType = new TypeToken<List<MovieDto>>(){}.getType();
        return modelMapper.map(movies, targetListType);
    }

    private Movie findById(long id) {
        return movies.stream().filter(m->m.getId() == id).findAny().orElseThrow(() -> new IllegalArgumentException());
    }

    public MovieDto findMovieById(long id) {
        Movie found = findById(id);
        return modelMapper.map(found,MovieDto.class);
    }


    public MovieDto addMovie(CreateMovieCommand command) {
        Movie movie = new Movie(idGenerator.incrementAndGet()
                        ,command.getTitle()
                        ,command.getLength()
        );

        movies.add(movie);

        return modelMapper.map(movie, MovieDto.class);
    }


    public void deleteMovie(Long id) {
        Movie found = findById(id);
        movies.remove(found);
    }

    public void addRate(long id, RateCommand command) {
        Movie found = findById(id);
        found.addRate(command.getRate());
    }
}
