package adventure_time.core.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

//@Entity
@Table (name = "tours")
public class Tours {

    private final static Long TWELVE_HOURS = 43200000L;

    @Id
    @Column (name = "id")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event", nullable = false)
    private Events event;       // foreign key

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "tour_start", nullable = false)
    private Date tourStart;

    @Column (name = "ticket_coast", nullable = false, precision = 8, scale = 2)
    private Double ticketCoast;

    @Column (name = "available_tickets", nullable = false)
    private Integer availableTickets;

    @Column (name = "completed")
    private Boolean completed;

    public Tours () {}

    public Tours(Events event, Date tourStart, Double ticketCoast) {
        this.event = event;
        this.tourStart = tourStart;
        this.ticketCoast = ticketCoast;
        this.availableTickets = event.getMaxNumberParticipants();
        this.completed = false;

    }

    public Boolean isCompleted() {
        return this.completed;
    }

    public Boolean isComing() {
        Date now = new Date();
        return (this.tourStart.getTime() - now.getTime()) < TWELVE_HOURS;
    }

    public Boolean decAvailableTickets() {
        if (this.availableTickets > 0) {
            if (--this.availableTickets == 0) {
                this.completed = true;
            }
            return true;
        }
        return false;
    }

    //*******************************************


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Events getEvent() {
        return event;
    }

    public void setEvent(Events event) {
        this.event = event;
    }

    public Date getTourStart() {
        return tourStart;
    }

    public void setTourStart(Date tourStart) {
        this.tourStart = tourStart;
    }

    public Double getTicketCoast() {
        return ticketCoast;
    }

    public void setTicketCoast(Double ticketCoast) {
        this.ticketCoast = ticketCoast;
    }

    public Integer getAvailableTickets() {
        return availableTickets;
    }

    public void setAvailableTickets(Integer availableTickets) {
        this.availableTickets = availableTickets;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tours tours = (Tours) o;
        return Objects.equals(id, tours.id) &&
                Objects.equals(event, tours.event) &&
                Objects.equals(tourStart, tours.tourStart) &&
                Objects.equals(ticketCoast, tours.ticketCoast) &&
                Objects.equals(availableTickets, tours.availableTickets) &&
                Objects.equals(completed, tours.completed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, event, tourStart, ticketCoast, availableTickets, completed);
    }

    @Override
    public String toString() {
        return "Tours{" +
                "id=" + id +
                ", event=" + event +
                ", tourStart=" + tourStart +
                ", ticketCoast=" + ticketCoast +
                ", availableTickets=" + availableTickets +
                ", completed=" + completed +
                '}';
    }
}
