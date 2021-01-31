package dental_clinic.core.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="manipulation")
public class Manipulation {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="manipulation_type", nullable = false)
    private String manipulation_type;

    @Column(name="price", nullable = false)
    private Integer price;

    @Column(name="isActive", nullable = false)
    private boolean isActive = true;

    public Manipulation() { }

    public Manipulation(String manipulation_type, Integer price) {
        this.manipulation_type = manipulation_type;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getManipulation_type() {
        return manipulation_type;
    }

    public void setManipulation_type(String manipulation_type) {
        this.manipulation_type = manipulation_type;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manipulation that = (Manipulation) o;
        return isActive == that.isActive && Objects.equals(manipulation_type, that.manipulation_type) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(manipulation_type, price, isActive);
    }

    @Override
    public String toString() {
        return "Manipulation:\n" +
                "id: " + id +
                ", manipulation_type: " + manipulation_type +
                ", price: " + price +
                ", isActive: " + isActive;
    }
}
