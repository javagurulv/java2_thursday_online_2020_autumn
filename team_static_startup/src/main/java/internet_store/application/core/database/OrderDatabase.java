package internet_store.application.core.database;

import internet_store.application.core.domain.Order;

import java.util.List;
import java.util.Optional;

public interface OrderDatabase {

    Long add(Order order);

    Optional<Order> findById(Long id);

    List<Order> findAll();

}
