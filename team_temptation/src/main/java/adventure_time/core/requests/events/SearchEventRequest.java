package adventure_time.core.requests.events;

public class SearchEventRequest {

    private Long eventId;
    private String eventName;

    public SearchEventRequest(Long eventId, String eventName) {
        this.eventId = eventId;
        this.eventName = eventName;
    }

    public Long getEventId() {
        return eventId;
    }

    public String getEventName() {
        return eventName;
    }

}
