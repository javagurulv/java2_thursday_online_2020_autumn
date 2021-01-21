package internet_store.application.core.domain;

import javax.persistence.*;
import java.util.Objects;

// @Entity
// @Table(name = "customers")
public class Customer {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
    private Long customerId;
//    @Column(name = "first_name")
    private String customerFirstName;
//    @Column(name = "second_name")
    private String customerSecondName;
//    @Column(name = "phone")
    private String customerPhone;
//    @Column(name = "email")
    private String customerEmail;

    public Customer() {}

    public Customer(String customerFirstName, String customerSecondName) {
        this.customerFirstName = customerFirstName;
        this.customerSecondName = customerSecondName;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public String getCustomerSecondName() {
        return customerSecondName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public void setCustomerSecondName(String customerSecondName) {
        this.customerSecondName = customerSecondName;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(customerId, customer.customerId) &&
                Objects.equals(customerFirstName, customer.customerFirstName) &&
                Objects.equals(customerSecondName, customer.customerSecondName) &&
                Objects.equals(customerPhone, customer.customerPhone) &&
                Objects.equals(customerEmail, customer.customerEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, customerFirstName, customerSecondName, customerPhone, customerEmail);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerFirstName='" + customerFirstName + '\'' +
                ", customerSecondName='" + customerSecondName + '\'' +
                ", customerPhone='" + customerPhone + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                '}';
    }

}

