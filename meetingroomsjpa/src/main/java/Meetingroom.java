import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "meetingrooms")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Meetingroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int width;

    private  int length;

    public Meetingroom(String name, int width, int length) {
        this.name = name;
        this.width = width;
        this.length = length;
    }
}
