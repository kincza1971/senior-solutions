import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MeetingRoom {

    private int roomId;
    private final String name;
    private final int width;
    private final int length;
    private final int area;
    private final List<Meeting> meetings = new ArrayList<>();

    public MeetingRoom(int roomId, String name, int width, int length, int area) {
        this.roomId = roomId;
        this.name = name;
        this.width = width;
        this.length = length;
        this.area = area;
    }
    public MeetingRoom(int roomId, String name, int width, int length) {
        this.roomId = roomId;
        this.name = name;
        this.width = width;
        this.length = length;
        this.area = width * length;
    }

    public MeetingRoom(String name, int width, int length) {
        this.name = name;
        this.width = width;
        this.length = length;
        this.area = width * length;
    }

    public int getRoomId() {
        return roomId;
    }

    public String getName() {
        return name;
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public int getArea() {
        return area;
    }

    public List<Meeting> getMeetings() {
        return List.copyOf(meetings);
    }

    @Override
    public String toString() {
        return "{" +
                "név='" + name + '\'' +
                ", szélesség=" + width +
                ", Hosszúság=" + length +
                ", terület=" + area +
                '}' + System.lineSeparator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeetingRoom that = (MeetingRoom) o;
        return getWidth() == that.getWidth() && getLength() == that.getLength() && getArea() == that.getArea() && getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getWidth(), getLength(), getArea());
    }

    public boolean addMeetingIfFree(Meeting meeting) {
        if (isFree (meeting)) {
            meetings.add(new Meeting(meeting,getName()));
            return true;
        }
        return false;
    }

    private boolean isFree(Meeting meeting) {
        return meetings
                .stream()
                .noneMatch(m -> isCollate(m,meeting));
    }

    private boolean isCollate(Meeting m1, Meeting m2) {
        LocalDateTime m1Start = m1.getStartTime();
        LocalDateTime m1End = m1.getEndTime();
        LocalDateTime m2Start = m2.getStartTime();
        LocalDateTime m2End = m2.getEndTime();
        if (m1Start.isBefore(m2End) && m1End.isAfter(m2End)) {
            return true;
        }
        if (m1Start.isBefore(m2Start) && m1End.isAfter(m2Start)) {
            return true;
        }
        return m2Start.isBefore(m1Start) && m2End.isAfter(m1End);
    }


}

//Tárgyalók nyilvántartása
//Az előkészítő projektmunka azokra a témakörökre fókuszál, melyeket érdemes átismételni a haladó képzés előtt.
//
//Ezek:
//
//Többrétegű alkalmazás architektúra: repository (DAO), service és controller réteg
//DAO réteg implementálása JdbcTemplate használatával
//Streamek használata
//Tesztesetek írása
//Feladat
//Készíts egy programot, mely egy irodaházban lévő tárgyalókat tartja nyilván! A tárgyalókat fel lehet venni,
// ki lehet listázni, és lehet keresni.
//
//Egy tárgyalóhoz a következő adatokat kell nyilvántartani:
//
//Tárgyaló egyedi azonosítója (generált egész szám)
//Tárgyaló neve
//Tárgyaló szélessége méterben
//Tárgyaló hosszúsága méterben
