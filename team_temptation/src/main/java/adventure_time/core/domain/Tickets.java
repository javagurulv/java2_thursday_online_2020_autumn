package adventure_time.core.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

//@Entity
@Table (name = "tickets")
public class Tickets {

    @Id
    @Column (name = "id")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn (name = "tour", nullable = false)
    private Tours tour;

    @ManyToOne
    @JoinColumn (name = "customer")
    private Customers customer;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "sale_date")
    private Date saleDate;

    @Column (name = "available")
    private Boolean available;

    public Tickets() {}

    public Tickets(Tours tour) {
        this.tour = tour;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tours getTour() {
        return tour;
    }

    public void setTour(Tours tour) {
        this.tour = tour;
    }

    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Tickets{" +
                "id=" + id +
                ", tour=" + tour +
                ", customer=" + customer +
                ", saleDate=" + saleDate +
                ", available=" + available +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tickets tickets = (Tickets) o;
        return Objects.equals(id, tickets.id) &&
                Objects.equals(tour, tickets.tour) &&
                Objects.equals(customer, tickets.customer) &&
                Objects.equals(saleDate, tickets.saleDate) &&
                Objects.equals(available, tickets.available);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tour, customer, saleDate, available);
    }
}
