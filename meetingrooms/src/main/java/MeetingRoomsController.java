import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MeetingRoomsController {

    private static final String MENU_TO_PRINT = "0. Tárgyaló rögzítése" + System.lineSeparator()+
            "1. Tárgyalók névsorrendben" + System.lineSeparator()+
            "2. Tárgyalók név alapján visszafele sorrendben" + System.lineSeparator()+
            "3. Minden második tárgyaló" + System.lineSeparator()+
            "4. Területek" + System.lineSeparator()+
            "5. Keresés pontos név alapján" + System.lineSeparator()+
            "6. Keresés névtöredék alapján" + System.lineSeparator()+
            "7. Keresés terület alapján"+ System.lineSeparator()+
            "X. KILÉPÉS " + System.lineSeparator() + System.lineSeparator() +
            "Kérem válasszon a fenti menüpontok közül";

    public static final String EXIT_CODE = "X";

    private final Scanner scr = new Scanner(System.in);

    //private final MeetingRoomsServices service = new MeetingRoomsServices(new InMemoryMeetingRoomsRepository());
    private final MeetingRoomsServices service = new MeetingRoomsServices(new InDatabaseMeetingRoomsRepository());

    private void printMenu() {
        System.out.println(MENU_TO_PRINT);
    }

    private String getSelected() {
        return scr.nextLine().trim();
    }

    private void callSelected(String selected) {

        switch (selected) {
            case "0" -> getAndSaveMeetingRoomData();
            case "1" -> System.out.println(service.getNames());
            case "2" -> System.out.println(service.getNamesReversed());
            case "3" -> System.out.println(service.getNamesEven());
            case "4" -> System.out.println(service.getAreas());
            case "5" -> findByName();
            case "6" -> findByNamePart();
            case "7" -> findGreater();
        }
    }

    private void findByName() {
        System.out.println("Kérem adja meg a keresett tárgyaló nevét:");
        String name = scr.nextLine();
        if (badName(name)) {
            return;
        }
        Optional<MeetingRoom> isFound = service.findByName(name);
        isFound.ifPresent(System.out::println);
    }

    private void findByNamePart() {
        System.out.println("Kérem adja meg a keresett tárgyaló nevrészletét:");
        String name = scr.nextLine();
        if (badName(name)) {
            return;
        }
        List<String> found = service.findByNamePart(name);
        if (!found.isEmpty()) {
            System.out.println(found);
        }
    }

    private void findGreater() {
        System.out.println("Kérem adja meg a területet, aminél nagyobb tárgyalót keres:");
        String sizeLimitStr = scr.nextLine();
        int sizeLimit;
        try {
            sizeLimit = Integer.parseInt(sizeLimitStr);
            if (sizeLimit <0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException ne) {
            System.out.println("Érvénytelen adat - a terület kötelezően pozitív egész szám");
            return;
        }
        System.out.println(service.findGreater(sizeLimit));
    }

    private void getAndSaveMeetingRoomData() {
        System.out.println("Kérem, adja meg  a tárgyaló nevét:");
        String name = scr.nextLine();
        System.out.println("Kérem adja meg a tárgyaló szélességét:");
        String width = scr.nextLine();
        System.out.println("Kérem adja meg a tárgyaló hosszát:");
        String length = scr.nextLine();
        System.out.println("Név=" + name + ", Szélesség=" + width + ", Hossz="+length);
        System.out.println("Helyesek az adatok?  (I/N)");
        if (scr.nextLine().trim().equalsIgnoreCase("I")) {
            convertAndSave(name, width, length);
        }
    }

    private boolean badName(String name) {
        if (name == null || name.isBlank()) {
            System.out.println("Érvénytelen adat - a név megadása kötelező");
            return true;
        }
        return false;
    }

    private void convertAndSave(String name, String width, String length) {
        if (badName(name)) {
            return;
        }
        try {
            int widthNum = Integer.parseInt(width);
            int lengthNum = Integer.parseInt(length);
            if (widthNum <0 || lengthNum <0) {
                System.out.println("Érvénytelen adat - a szélesség és a hosszúság kötelezően pozitív szám");
            }
            service.save(name, widthNum, lengthNum);
        } catch (NumberFormatException ne) {
            System.out.println("Érvénytelen adat - a szélesség és a hosszúság kötelezően egész szám");
        }
    }

    public void start() {
        String selected;
        do {
            printMenu();
            selected = getSelected();
            callSelected(selected);
        } while (!selected.equals(EXIT_CODE));
    }

    public static void main(String[] args) {
        new MeetingRoomsController().start();
    }
}
//Majd írj ki a felhasználónak egy menüt, a következőképp:
//
//0. Tárgyaló rögzítése
//1. Tárgyalók névsorrendben
//2. Tárgyalók név alapján visszafele sorrendben
//3. Minden második tárgyaló
//4. Területek
//5. Keresés pontos név alapján
//6. Keresés névtöredék alapján
//7. Keresés terület alapján
//Majd kérj be a felhasználótól egy számot! Hajtsd végre a számhoz tartozó funkciót.
//
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
//Technológia
//Az alkalmazást kétféleképp kell megvalósítani:
//
//Az első az adatokat tárolja memóriában egy listában!
//A második az adatokat tárolja adatbázisban!
//Az alkalmazás többrétegű legyen, legyen egy MeetingRoomsController, egy MeetingRoomsService és egy MeetingRoomsRepository. A
// z első kettő egy osztály legyen, míg a második egy interfész. Adatokat csak a controller kérjen be.
// A repository-nak két implementációja van: InMemoryMeetingRoomsRepository és egy MariaDbMeetingRoomsRepository.
//
//Az adatbázishoz JdbcTemplate-et használj! Flyway-jel hozd létre a táblát!
//
//Írjál teszteseteket a service réteghez! Ahhoz, hogy a teszteseteknek ne legyen egymásra hatása, kell egy külön metódus,
// mely az adatbázisból kitörli az adatokat. Ezt minden teszteset futása előtt meg kell hívni.
