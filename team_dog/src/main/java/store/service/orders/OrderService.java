package store.service.orders;

import store.dtos.ItemDTO;
import store.dtos.OrderDTO;
import store.entity.items.Item;
import store.entity.orders.Order;

import java.util.List;

public interface OrderService {

    Long createNewOrder(OrderDTO orderDTO);

    List<Order> getAllOrder();

    void addToBasket(Item item);

    void removeFromBasket(Long itemId);

    List<Item> getBasket();

    OrderDTO getOrder(Long id);

    List<Item> getOrderItems(Long id);
}
