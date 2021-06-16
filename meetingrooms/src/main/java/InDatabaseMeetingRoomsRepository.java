import org.flywaydb.core.Flyway;
import org.mariadb.jdbc.MariaDbDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InDatabaseMeetingRoomsRepository implements MeetingRoomsRepository{
    MariaDbDataSource mariaDbDataSource = new MariaDbDataSource();


    private final JdbcTemplate jdbcTemplate;

    public InDatabaseMeetingRoomsRepository() {
         try {
            mariaDbDataSource.setUrl("jdbc:mariadb://localhost:3306/meetingrooms?useUnicode=true");
            mariaDbDataSource.setUser("meetingroomuser");
            mariaDbDataSource.setPassword("user");

            jdbcTemplate = new JdbcTemplate(mariaDbDataSource);

            Flyway flyway = Flyway.configure().dataSource(mariaDbDataSource).load();
            flyway.migrate();

        } catch (SQLException sqle) {
            throw new IllegalStateException("Cannot create datasource", sqle);
        }
    }

    @Override
    public void save(MeetingRoom meetingRoom) {
        try (Connection conn = mariaDbDataSource.getConnection())
        {
            try (
                    PreparedStatement smt1 = conn.prepareStatement(
                            "Insert into meetingrooms (name, width, length) values (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            )
            {
                smt1.setString(1, meetingRoom.getName());
                smt1.setInt(2,meetingRoom.getWidth());
                smt1.setInt(3,meetingRoom.getLength());
                smt1.execute();
                ResultSet rs = smt1.getGeneratedKeys();
                saveMeetings(meetingRoom, conn, rs);
            } catch (SQLException sqle) {
                conn.rollback();
                throw new IllegalStateException("Meetingroom mentése sikertelen ", sqle);
            }

        } catch (SQLException sqle) {
            throw new IllegalStateException("Nem sikerült az adatbázishoz kapcsolódni", sqle);
        }
//        jdbcTemplate.update("insert into meetingrooms (name, width, length) values (?,?,?)",name,width,length);
//
    }

    private void saveMeetings(MeetingRoom meetingRoom, Connection conn, ResultSet rs) throws SQLException {
        int key =0;
        if (rs.next()) {
            key = rs.getInt(1);

            for (Meeting meeting : meetingRoom.getMeetings()) {
                try (PreparedStatement smt2 = conn.prepareStatement("INSERT INTO meetings (organizer, from_dt, to_dt, mr_id ) values (?,?,?,?)")) {
                    smt2.setString(1,meeting.getOrganizer());
                    smt2.setTimestamp(2,Timestamp.valueOf(meeting.getStartTime()));
                    smt2.setTimestamp(3,Timestamp.valueOf(meeting.getEndTime()));
                    smt2.setInt(4,key);
                    smt2.execute();
                } catch (SQLException sqle) {
                    conn.rollback();
                    throw new IllegalStateException("Cannot save meeting ", sqle);
                }
            }
        }
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
    public Optional<MeetingRoom> findByName(String name) {
        return Optional.ofNullable(
                jdbcTemplate.queryForObject("SELECT id, name, width, length, area from meetingrooms Where name = ?",
                        new Object[]{name},
                        (rs, i) ->  meetingRoomFactory(rs)
                )
        );
    }

    @Override
    public List<String> findByNamePart(String namePart) {
        namePart = "%"+namePart.toLowerCase()+"%";
        return jdbcTemplate.query("SELECT id, name, width, length, area from meetingrooms Where name like ?",
                new Object[]{namePart},
                (rs, i) ->  stringBeautifier(rs));
    }

    @Override
    public List<String> findGreater(int sizeLimit) {
        return jdbcTemplate.query("SELECT id, name, width, length, area from meetingrooms Where area > ?",
                new Object[]{sizeLimit},
                (rs,i) ->  stringBeautifier(rs)
        );
    }

    @Override
    public List<String> getMeetings() {
        try (Connection conn = mariaDbDataSource.getConnection();
             PreparedStatement smnt = conn.prepareStatement(
                     "SELECT r.name, m.organizer, m.from_dt, m.to_dt  FROM meetings m INNER JOIN meetingrooms r " +
                             "ON m.mr_id = r.id " + "ORDER BY r.name;")
        ){
            ResultSet rs = smnt.executeQuery();
            return rsBeautifier(rs);

        } catch (SQLException sqle) {
            throw new IllegalStateException("Cannot connect to database ", sqle);
        }
    }

    private List<String> rsBeautifier(ResultSet rs) throws SQLException {
        List<String> result= new ArrayList<>();
        StringBuilder line;
        String name = null;
        String organizer = null;
        String prevName =null;
        while (rs.next()) {
            name = rs.getString("name");
            if (!(name).equals(prevName)) {
                line =new StringBuilder();
                line.append(name).append(System.lineSeparator());
                result.add(line.toString());
                prevName = name;
            }
            line = new StringBuilder();
            line.append(" ".repeat(35));
            organizer = rs.getString("organizer");
            line.replace(5,organizer.length()+5,organizer).append("\t");
            line.append(rs.getTimestamp("from_dt")).append("\t");
            line.append(rs.getTimestamp("to_dt")).append(System.lineSeparator());
            result.add(line.toString());
        }
        return result;
    }

    private String stringBeautifier(ResultSet rs) throws SQLException {
        return "{" +
                "név='" + rs.getString("name") + '\'' +
                ", szélesség=" + rs.getInt("width") +
                ", Hosszúság=" + rs.getInt("length") +
                ", terület=" + rs.getInt("area") +
                '}' + System.lineSeparator();

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
}
