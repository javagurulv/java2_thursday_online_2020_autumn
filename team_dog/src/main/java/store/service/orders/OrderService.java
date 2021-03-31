package store.service.orders;

import store.entity.items.Item;
import store.entity.orders.Order;

import java.util.List;

public interface OrderService {

    Order createNewOrder();

    List<Order> getAllOrder();

    void addToBasket(Item item);

    void removeFromBasket(Long itemId);

    List<Item> getBasket();


}
