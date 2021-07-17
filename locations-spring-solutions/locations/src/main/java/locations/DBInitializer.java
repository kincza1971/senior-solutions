package locations;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DBInitializer implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        jdbcTemplate.execute("create table locations (" +
                "id bigint auto_increment, name varchar(255), lon float, lat float, primary key (id))");

        jdbcTemplate.execute("insert into locations (name, lon, lat) values ('Budapest', 43.857, 17.558)");
        jdbcTemplate.execute("insert into locations (name, lon, lat) values ('Debrecen', 43.857, 19.558)");
    }
}
