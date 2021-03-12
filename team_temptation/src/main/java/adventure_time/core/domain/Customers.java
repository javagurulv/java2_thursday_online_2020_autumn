package adventure_time.core.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity                     // этот класс используется для ORM маппинга
@Table(name="customers")    // название таблицы в базе данных на которую мапятся свойства этого класса
public class Customers {

    @Id                     // поле является первичным ключём
    @Column(name="id")      // указывает название колонки в таблице базы
    @GeneratedValue(strategy = GenerationType.IDENTITY) // стратегия генерации первичного ключа
    private Long customerId;

    @Column(name="name", nullable = false, length = 50)
    private String customerName;

    @Column(name="email", unique = true, nullable = false, length = 30)
    private String customerEmail;

    @Column(name="phone", unique = true, nullable = false, length = 12)
    private String customerPhone;

    @Column(name="password", nullable = false, length = 20)
    private String customerPassword;

    public Customers() {
    }

    public Customers(String customerName, String customerEmail, String customerPhone, String customerPassword) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
        this.customerPassword = customerPassword;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customers customers = (Customers) o;
        return Objects.equals(customerId, customers.customerId) &&
                Objects.equals(customerName, customers.customerName) &&
                Objects.equals(customerEmail, customers.customerEmail) &&
                Objects.equals(customerPhone, customers.customerPhone) &&
                Objects.equals(customerPassword, customers.customerPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, customerName, customerEmail, customerPhone, customerPassword);
    }

    @Override
    public String toString() {
        return "Customers{" +
                "customerID=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", customerPhone='" + customerPhone + '\'' +
                ", customerPassword='" + customerPassword + '\'' +
                '}';
    }
}
