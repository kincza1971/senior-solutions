package moviesspring;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    private Long id;
    private String title;
    private int length;
    private double avgRate;
    List<Integer> rates = new ArrayList<>();

    public Movie(Long id,String title, int length) {
        this.id = id;
        this.title = title;
        this.length = length;
    }

    public void addRate(int rate) {
        rates.add(rate);
        this.avgRate = rates.stream().mapToInt(Integer::intValue).summaryStatistics().getAverage();
    }
}
