package adventure_time.core.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="events")
public class Events {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long eventId;

    @Column (name = "name", nullable = false, unique = true, length = 50)
    private String eventName;

    @Column (name = "kind", nullable = false, length = 30)
    private String eventKind;

    @Column (name = "duration", nullable = false)
    private Integer durationHours;

    @Column (name = "max_guys", nullable = false)
    private Integer maxNumberParticipants;

    @Column (name = "min_guys", nullable = false)
    private Integer minNumberParticipants;

    @Column (name = "route", nullable = false, length = 50)
    private String route;

    @Column (name = "detail", nullable = false, length = 256)
    private String detailsDescription;

    public Events() {}

    public Events(String eventName, String eventKind, Integer durationHours,
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

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventKind() {
        return eventKind;
    }

    public void setEventKind(String eventKind) {
        this.eventKind = eventKind;
    }

    public Integer getDurationHours() {
        return durationHours;
    }

    public void setDurationHours(Integer durationHours) {
        this.durationHours = durationHours;
    }

    public Integer getMaxNumberParticipants() {
        return maxNumberParticipants;
    }

    public void setMaxNumberParticipants(Integer maxNumberParticipants) {
        this.maxNumberParticipants = maxNumberParticipants;
    }

    public Integer getMinNumberParticipants() {
        return minNumberParticipants;
    }

    public void setMinNumberParticipants(Integer minNumberParticipants) {
        this.minNumberParticipants = minNumberParticipants;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getDetailsDescription() {
        return detailsDescription;
    }

    public void setDetailsDescription(String detailsDescription) {
        this.detailsDescription = detailsDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Events events = (Events) o;
        return Objects.equals(eventId, events.eventId) &&
                Objects.equals(eventName, events.eventName) &&
                Objects.equals(eventKind, events.eventKind) &&
                Objects.equals(durationHours, events.durationHours) &&
                Objects.equals(maxNumberParticipants, events.maxNumberParticipants) &&
                Objects.equals(minNumberParticipants, events.minNumberParticipants) &&
                Objects.equals(route, events.route) &&
                Objects.equals(detailsDescription, events.detailsDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, eventName, eventKind, durationHours, maxNumberParticipants, minNumberParticipants, route, detailsDescription);
    }

    @Override
    public String toString() {
        return "Event[" +
                "Id=" + eventId +
                ", kind='" + eventKind + '\'' +
                ", duration=" + durationHours +
                ", max=" + maxNumberParticipants +
                ", min=" + minNumberParticipants +
                ", route='" + route + '\'' +
                ", name='" + eventName + '\'' +
                ", details='" + detailsDescription + '\'' +
                ']';
    }
}
