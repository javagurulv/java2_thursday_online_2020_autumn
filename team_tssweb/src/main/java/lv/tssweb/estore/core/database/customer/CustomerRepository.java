package lv.tssweb.estore.core.database.customer;

import lv.tssweb.estore.core.domain.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {

    Long addCustomer(Customer customer);

    boolean deleteByCustomerId(Long id);

    List<Customer> findByFirstName(String customerName);

    Optional<Customer> findByCustomerId(Long id);

    List<Customer> findAll();

    boolean changeFirstName(Long id, String newFirstName);

}
