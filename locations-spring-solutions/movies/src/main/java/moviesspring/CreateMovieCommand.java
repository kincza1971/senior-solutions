package moviesspring;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateMovieCommand {
    private String title;
    private int length;
}
