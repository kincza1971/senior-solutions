import org.flywaydb.core.Flyway;
import org.mariadb.jdbc.MariaDbDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InDatabaseMeetingRoomsRepository implements MeetingRoomsRepository{


    private JdbcTemplate jdbcTemplate;

    public InDatabaseMeetingRoomsRepository() {

        try {
            MariaDbDataSource mariaDbDataSource = new MariaDbDataSource();
            mariaDbDataSource.setUrl("jdbc:mariadb://localhost:3306/meetingrooms?useUnicode=true");
            mariaDbDataSource.setUser("meetingroomuser");
            mariaDbDataSource.setPassword("user");

            jdbcTemplate = new JdbcTemplate(mariaDbDataSource);

            Flyway flyway = Flyway.configure().dataSource(mariaDbDataSource).load();
            flyway.migrate();

        } catch (SQLException sqle) {
            throw new IllegalStateException("Cannot create datasource");
        }
    }

    @Override
    public void save(String name, int width, int length) {
        jdbcTemplate.update("insert into meetingrooms (name, width, length) values (?,?,?)",name,width,length);

    }

    @Override
    public List<String> getNames() {
        return jdbcTemplate.query("select name from meetingrooms order by name",
                (rs,i) -> rs.getString("name")
        );
    }

    @Override
    public List<String> getNamesReversed() {
        return jdbcTemplate.query("select name from meetingrooms order by name desc",
                (rs,i) -> rs.getString("name")
        );
    }

    @Override
    public List<String> getNamesEven() {
        List<String> found = jdbcTemplate.query("SELECT name FROM meetingrooms ORDER BY NAME",
                (rs,i) -> rs.getString("name")
        );
        return IntStream.range(0,found.size())
                .filter(i -> i%2 != 0)
                .mapToObj(found::get)
                .collect(Collectors.toList());
    }

    @Override
    public List<MeetingRoom> getAreas() {
        return jdbcTemplate.query("SELECT id, name, width, length, area FROM meetingrooms ORDER BY area DESC"
                ,(rs, i) -> meetingRoomFactory(rs)
        );
    }

    @Override
    public MeetingRoom findByName(String name) {
        return jdbcTemplate.queryForObject("SELECT id, name, width, length, area from meetingrooms Where name = ?",
                new Object[]{name},
                (rs, i) ->  meetingRoomFactory(rs)
        );
    }

    @Override
    public List<String> findByNamePart(String namePart) {
        namePart = "%"+namePart+"%";
        List<MeetingRoom>  found = jdbcTemplate.query("SELECT id, name, width, length, area from meetingrooms Where name like ?",
                new Object[]{namePart.toLowerCase()},
                (rs, i) ->  meetingRoomFactory(rs));
        return convertToStringList(found);
    }

    @Override
    public List<String> findGreater(int sizeLimit) {
        List<MeetingRoom>  found = jdbcTemplate.query("SELECT id, name, width, length, area from meetingrooms Where area > ?",
                new Object[]{sizeLimit},
                (rs,i) ->  meetingRoomFactory(rs)
        );
        return convertToStringList(found);
    }
    private MeetingRoom meetingRoomFactory(ResultSet rs) throws SQLException {
        return new MeetingRoom(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("width"),
                rs.getInt("length"),
                rs.getInt("area")
        );
    }
    private List<String> convertToStringList(List<MeetingRoom> found) {
        return found.stream()
                .map(MeetingRoom::toString)
                .collect(Collectors.toList());
    }
}
