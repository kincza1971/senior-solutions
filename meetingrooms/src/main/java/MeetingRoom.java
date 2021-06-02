public class MeetingRoom {

    private final int roomId;
    private final String name;
    private final int width;
    private final int length;
    private final int area;

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

    @Override
    public String toString() {
        return "{" +
                "név='" + name + '\'' +
                ", azonosító=" + roomId +
                ", szélesség=" + width +
                ", Hosszúság=" + length +
                ", terület=" + area +
                '}' + System.lineSeparator();
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
