package locations;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class LocationsDao {

    private JdbcTemplate jdbcTemplate;

    public List<Location> findAllLocations() {
        return jdbcTemplate.query("select id, name, lat, lon from locations",
                (resultSet, i) -> {
                    long id = resultSet.getLong("id");
                    String name = resultSet.getString("name");
                    double lon = resultSet.getDouble("lon");
                    double lat = resultSet.getDouble("lat");

                    return new Location(id,name, lon, lat);
                });
    }
}
