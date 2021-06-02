import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InMemoryMeetingRoomsRepository implements MeetingRoomsRepository{

    private List<MeetingRoom> rooms =  new ArrayList<>();
    private int aktRoomId =0;
    private Locale locale = new Locale("hu","HU");
    private final Comparator<String> comparator = new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            return Collator.getInstance(locale).compare(o1,o2);
        }
    };

    private final Comparator<String> comparatorReverse = new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            return Collator.getInstance(locale).compare(o2,o1);
        }
    };

    @Override
    public void save(String name, int width, int length) throws IllegalArgumentException,IllegalStateException{
        rooms.add(new MeetingRoom(aktRoomId+1, name, width, length));
        aktRoomId++;
    }

    @Override
    public List<String> getNames() {
        return rooms.stream()
                .map(MeetingRoom::getName)
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getNamesReversed() {
        return rooms.stream()
                .map(MeetingRoom::getName)
                .sorted(comparatorReverse)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getNamesEven() {

        List<String> sortedList = rooms.stream()
                .map(MeetingRoom::getName)
                .sorted(comparator)
                .collect(Collectors.toList());

        return IntStream.range(0, sortedList.size())
                .filter(i -> i % 2 !=0)
                .mapToObj(sortedList::get)
                .collect(Collectors.toList());
    }

    @Override
    public List<MeetingRoom> getAreas() {
        return List.copyOf(rooms);
    }

    @Override
    public MeetingRoom findByName(String name) {
        List<MeetingRoom> filtered = rooms.stream()
                .filter(m -> m.getName().toLowerCase(locale).equals(name.toLowerCase(locale)))
                .collect(Collectors.toList());
        if (filtered.isEmpty()) {
            return null;
        }
        return filtered.get(0);
    }

    @Override
    public List<String> findByNamePart(String namePart) {
        return rooms.stream()
                .filter(m -> m.getName().toLowerCase(locale).contains(namePart.toLowerCase(locale)))
                .map(MeetingRoom::toString)
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findGreater(int sizeLimit) {
        return rooms.stream()
                .filter(m -> m.getArea()>sizeLimit)
                .map(MeetingRoom::toString)
                .sorted(comparator)
                .collect(Collectors.toList());
    }
}
