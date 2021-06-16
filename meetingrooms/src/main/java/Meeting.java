import java.time.LocalDateTime;

public class Meeting {
    private String organizer;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String meetingRoomName;

    public Meeting() {
    }

    public Meeting(String organizer, LocalDateTime startTime, LocalDateTime endTime, String meetingRoomName) {
        this.organizer = organizer;
        this.startTime = startTime;
        this.endTime = endTime;
        this.meetingRoomName = meetingRoomName;
    }

    public Meeting(String organizer, LocalDateTime startTime, LocalDateTime endTime) {
        this.organizer = organizer;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Meeting(Meeting meeting, String mrName) {
        this.meetingRoomName = mrName;
        this.organizer = meeting.getOrganizer();
        this.endTime = meeting.getEndTime();
        this.startTime = meeting.getStartTime();
    }

    public String getOrganizer() {
        return organizer;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public String getMeetingRoomName() {
        return meetingRoomName;
    }
}
