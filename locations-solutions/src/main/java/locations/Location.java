package locations;

import java.util.Objects;

public class Location {
    private String name;
    private double lat;
    private double lon;

    public Location(String name, double lat, double lon) {
        checkCoordinates(lat,lon);
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }

    private void checkCoordinates(double lat, double lon) {
        if (lat < -90 || lat > 90) {
            throw new IllegalArgumentException("Latitude must be between -90 and 90");
        }
        if (lon < -180 || lon > 180) {
            throw new IllegalArgumentException("Longitude must be between -180 and 180");
        }
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

    public boolean isOnPrimeMeridian() {
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



    private double distance(double lat1, double lat2, double lon1,
                                  double lon2, double el1, double el2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = el1 - el2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }

    public double distanceFrom(Location otherlocation) {
        return distance(getLat(),otherlocation.getLat()
                ,getLon(),otherlocation.getLon()
                ,0.0,0.0
        );
    }

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                '}';
    }

    public String toCsv() {
        StringBuilder sb = new StringBuilder();
        return sb
                .append(getName()).append(",")
                .append(getLat()).append(",")
                .append(getLon())
                .toString();
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