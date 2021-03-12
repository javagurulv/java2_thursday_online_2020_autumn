package adventure_time.core.requests.customers;

import adventure_time.core.domain.Customers;

public class UpdateCustomerRequest {

    private String name;
    private String email;
    private String phone;
    private String password;
    private Customers customer;

    public UpdateCustomerRequest(String name, String email, String phone, String password, Customers customer) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.customer = customer;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public Customers getCustomer() {
        return customer;
    }
}
