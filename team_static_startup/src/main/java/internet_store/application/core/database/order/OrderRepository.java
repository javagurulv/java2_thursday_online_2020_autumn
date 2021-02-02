package internet_store.application.core.database.order;

import internet_store.application.core.domain.Order;

import java.util.List;

public interface OrderRepository {

    Long add(Long shoppingCartId);

    Order findById(Long id);

    List<Order> findAll();

}
