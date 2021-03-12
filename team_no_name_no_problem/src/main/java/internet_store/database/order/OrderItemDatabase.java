package internet_store.database.order;

import internet_store.core.domain.Order;

import java.util.List;

public interface OrderItemDatabase {


    List<Order> getOrders();

    void addOrder(Order order);
}
