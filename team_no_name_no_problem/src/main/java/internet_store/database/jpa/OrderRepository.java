package internet_store.database.jpa;

import internet_store.core.domain.Order;
import internet_store.core.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrderRepository extends JpaRepository<OrderItem, Long> {

   List<OrderItem> findAll();
}


