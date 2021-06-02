import java.util.List;

public class MeetingRoomsServices {
    private final MeetingRoomsRepository repo;



    public MeetingRoomsServices(MeetingRoomsRepository repo) {
        this.repo = repo;
    }

    public void save(String name, int width, int length) {
        repo.save(name,width,length);
    }

    public List<String> getNames() {
        return repo.getNames();
    }

    public List<String> getNamesReversed() {
        return repo.getNamesReversed();
    }

    public List<String> getNamesEven() {
        return repo.getNamesEven();
    }

    public List<MeetingRoom> getAreas() {
        return repo.getAreas();
    }

    public MeetingRoom findByName(String name) {
        return repo.findByName(name);
    }

    public List<String> findByNamePart(String namePart) {
        return repo.findByNamePart(namePart);
    }

    public List<String> findGreater(int sizeLimit) {
        return repo.findGreater(sizeLimit);
    }

}