import java.util.List;
import java.util.Optional;

public interface MeetingRoomsRepository {
    void save(MeetingRoom meetingRoom);
    List<String> getNames();
    List<String> getNamesReversed();
    List<String> getNamesEven();
    List<MeetingRoom> getAreas();
    Optional<MeetingRoom> findByName(String name);
    List<String> findByNamePart(String namePart);
    List<String> findGreater(int sizeLimit);
}

//Tárgyaló rögzítése: kérd be az adatokat, és rögzítsd!
//Tárgyalók sorrendben: csak a neveket kell kiírni névsorrendben. Figyelj a magyar ékezetekre!
//Tárgyalók visszafele sorrendben: csak a neveket kell kiírni fordított névsorrendben.
//Minden második tárgyaló: minden második tárgyaló nevét kell kiírni, névsorrendben.
//Területek: ki kell írni minden tárgyaló nevét, szélességét, hosszúságát és területét, terület alapján csökkenő sorrendben
//Keresés pontos név alapján: kérj be a felhasználótól egy nevet, és annak a tárgyalónak írd ki a szélességét, hosszúságát
// és területét, melynek ez a neve. Ha nincs ilyen nevű, nem kell kiírni semmit.
//Keresés névtöredék alapján: kérj be a felhasználótól egy névtöredéket, és annak a tárgyalónak írd ki a szélességét,
// hosszúságát és területét, amelynek a nevében benne van ez a névtöredék! Ne számítson a kis- és nagybetű különbség!
// Név szerint növekvő sorrendben.
//Keresés terület alapján: kérj be egy egész számot, és csak azoknak a tárgyalóknak írd ki a nevét, szélességét,
// hosszúságát és területét, melyeknek a területe nagyobb, mint a felhasználó által beírt terület!

