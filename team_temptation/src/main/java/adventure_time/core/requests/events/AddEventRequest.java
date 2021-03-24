package adventure_time.core.requests.events;

public class AddEventRequest {

    private String eventName;
    private String eventKind;
    private Integer durationHours;
    private Integer maxNumberParticipants;
    private Integer minNumberParticipants;
    private String route;
    private String detailsDescription;

    public AddEventRequest(String eventName, String eventKind, Integer durationHours,
                           Integer maxNumberParticipants, Integer minNumberParticipants,
                           String route, String detailsDescription) {
        this.eventName = eventName;
        this.eventKind = eventKind;
        this.durationHours = durationHours;
        this.maxNumberParticipants = maxNumberParticipants;
        this.minNumberParticipants = minNumberParticipants;
        this.route = route;
        this.detailsDescription = detailsDescription;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventKind() {
        return eventKind;
    }

    public Integer getDurationHours() {
        return durationHours;
    }

    public Integer getMaxNumberParticipants() {
        return maxNumberParticipants;
    }

    public Integer getMinNumberParticipants() {
        return minNumberParticipants;
    }

    public String getRoute() {
        return route;
    }

    public String getDetailsDescription() {
        return detailsDescription;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setEventKind(String eventKind) {
        this.eventKind = eventKind;
    }

    public void setDurationHours(Integer durationHours) {
        this.durationHours = durationHours;
    }

    public void setMaxNumberParticipants(Integer maxNumberParticipants) {
        this.maxNumberParticipants = maxNumberParticipants;
    }

    public void setMinNumberParticipants(Integer minNumberParticipants) {
        this.minNumberParticipants = minNumberParticipants;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public void setDetailsDescription(String detailsDescription) {
        this.detailsDescription = detailsDescription;
    }
}
