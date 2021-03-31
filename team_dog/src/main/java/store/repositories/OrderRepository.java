package store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import store.entity.orders.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
