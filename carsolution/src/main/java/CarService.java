import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CarService {

    private List<Car> cars = new ArrayList<>();

    public CarService() {
        cars.add(
                new Car("Ferrari","Testarosa",3,CarState.KIVÁLÓ,
                        List.of(new KmState(LocalDate.of(2019,2,1),3000),
                                new KmState(LocalDate.of(2020,3,9),12000))
                )
        );
        cars.add(
                new Car("Lamborghini","Murcielago",4,CarState.KIVÁLÓ,
                        List.of(new KmState(LocalDate.of(2019,2,7),2000),
                                new KmState(LocalDate.of(2020,3,9),19000))
                )
        );
        cars.add(
                new Car("Zonta","Speedstaar",4,CarState.KIVÁLÓ,
                        List.of(new KmState(LocalDate.of(2020,2,7),2000),
                                new KmState(LocalDate.of(2021,3,9),10000))
                )
        );
    }
}
