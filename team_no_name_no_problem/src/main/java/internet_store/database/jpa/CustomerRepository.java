package internet_store.database.jpa;

import internet_store.core.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findAll();

    Optional<Customer> findById(Long id);

    boolean existsById(Long id);

    void deleteById(Long id);

    List<Customer> findCustomersByNameAndSurname(String name, String surname);

    List<Customer> findAllCustomersByName(String name);

    List<Customer> findAllCustomersBySurname(String surname);

}
