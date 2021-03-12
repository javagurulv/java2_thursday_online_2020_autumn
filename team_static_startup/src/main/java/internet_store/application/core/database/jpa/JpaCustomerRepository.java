package internet_store.application.core.database.jpa;

import internet_store.application.core.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaCustomerRepository extends JpaRepository<Customer, Long> {

//    @Query("delete from Customer c where c.customerId = :id")
//    Long deleteByCustomerId(@Param("id")Long id);

    Long deleteByCustomerId(Long id);

    @Query("select c from Customer c where c.customerFirstName = :firstname")
    List<Customer> findByFirstName(@Param("firstname") String customerFirstName);

    @Query("update Customer c SET c.customerFirstName = :name where c.customerId = :id")
    Long changeFirstName(@Param("id") Long id, @Param("name") String newFirstName);

}
