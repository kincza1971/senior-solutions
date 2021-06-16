package locations;

public class LocationParser {
    public static Location parse(String locString) {
        String[] parts = locString.split(",");
        return new Location(parts[0],Double.parseDouble(parts[1]),Double.parseDouble(parts[2]));
    }
}
// Legyen egy public Location parse(String text)metódusa, mely a nevet és a koordinátákat vesszővel elválasztva várja
// (pl. Budapest,47.497912,19.040235)! A tizedeshatároló karakter legyen a pont!
// Ez a metódus visszaad egy új példányt, kitöltve a megfelelő attribútum értékekkel.
// Írj rá egy LocationTestosztályt, valamint egy testParse()metódust, mely ezt a metódust teszteli!