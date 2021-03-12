package adventure_time.core.database.customers;

import adventure_time.core.domain.Customers;
import adventure_time.core.requests.Paging;

import java.util.List;
import java.util.Optional;


public interface CustomerRepository {

    boolean add(Customers customer);

    boolean delete (Long id);

    Optional<Customers> findById (Long id);

    Optional<Customers> findByEmail (String customerEmail);

    boolean updateCustomer (Customers customer, Long id);

    List<Customers> findCustomers (String query, Paging paging);

}
