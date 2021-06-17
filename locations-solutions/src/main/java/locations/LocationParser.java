package locations;

public class LocationParser {

    public Location parse(String locString) {
        stringValidator(locString);
        String[] parts = locString.split(",");
        return new Location(parts[0],Double.parseDouble(parts[1]),Double.parseDouble(parts[2]));
    }

    private void stringValidator(String str) {
        if (str == null || str.isBlank()) {
            throw new IllegalArgumentException("Location data string must not null or empty");
        }
    }
}
// Legyen egy public Location parse(String text)metódusa, mely a nevet és a koordinátákat vesszővel elválasztva várja
// (pl. Budapest,47.497912,19.040235)! A tizedeshatároló karakter legyen a pont!
// Ez a metódus visszaad egy új példányt, kitöltve a megfelelő attribútum értékekkel.
// Írj rá egy LocationTestosztályt, valamint egy testParse()metódust, mely ezt a metódust teszteli!