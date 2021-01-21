package internet_store.application.core.database;

import internet_store.application.core.domain.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDatabase {

    Long add(Customer customer);

    Optional<Customer> findById(Long id);

    List<Customer> findAll();

}
