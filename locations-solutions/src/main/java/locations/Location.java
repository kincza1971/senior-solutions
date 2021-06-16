package locations;

import java.util.Objects;

public class Location {
    private String name;
    private double lat;
    private double lon;

    public Location(String name, double lat, double lon) {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }

    public Location() {
    }

    public String getName() {
        return name;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public boolean isEquator() {
        return lat == 0.0;
    }

    public boolean inOnPrimeMeridian() {
        return lon == 0.0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Double.compare(location.getLat(), getLat()) == 0 && Double.compare(location.getLon(), getLon()) == 0 && getName().equals(location.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getLat(), getLon());
    }
}
//Hozz létre egy Locationosztályt, name, lat, lonattribútumokkal! A nameattribútum Stringtípusú legyen!
// A szélességi és hosszúsági koordinátákat külön doubletípusú attribútummal ábrázold!Legyenek getter/setter metódusai,
// és konstruktora, ahol mind a három attribútumát meg lehet adni!Hozz  létre  egy LocationParserosztályt,
// mely  feladata  szöveges  értékből  kinyerni  egy kedvenc hely adatait!
// Legyen egy public Location parse(String text)metódusa, mely a nevet és a koordinátákat vesszővel elválasztva várja
// (pl. Budapest,47.497912,19.040235)! A tizedeshatároló karakter legyen a pont!
// Ez a metódus visszaad egy új példányt, kitöltve a megfelelő attribútum értékekkel.
// Írj rá egy LocationTestosztályt, valamint egy testParse()metódust, mely ezt a metódust teszteli!