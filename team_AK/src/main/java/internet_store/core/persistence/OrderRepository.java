package internet_store.core.persistence;

import internet_store.core.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByNumber(String number);

    @Query(value = "SELECT * FROM order_for_client GROUP BY number limit ? offset ?", nativeQuery = true)
    List<Order> getLimitsProductsRecords(int limit, int offset);

    @Query(value = "SELECT COUNT(DISTINCT number) FROM order_for_client", nativeQuery = true)
    long countUniqOrders();

    boolean existsByNumber(String orderNumber);

    boolean existsByClientPhoneNumber(String phoneNumber);
}