package store.service.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.dtos.OrderDTO;
import store.entity.items.Item;
import store.entity.orders.Order;
import store.repositories.OrderRepository;
import store.service.utility.OrderWaresEntityService;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private List<Item> basket = new ArrayList<>();


    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderWaresEntityService orderWaresEntityService;

    @Override
    public Long createNewOrder(OrderDTO orderDTO) {
        Long orderId = generateOrderId();
        List<Long> itemIds = orderWaresEntityService.transformItemIds(basket);
        Order order = new Order(orderId, itemIds, orderDTO.getEmail(), orderDTO.getAddress(), orderDTO.getPhone(), orderDTO.getAdditionalInformation());
        orderRepository.save(order);
        basket.clear();
        return orderId;
    }

    @Override
    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    @Override
    public void addToBasket(Item item) {
        basket.add(item);
    }

    @Override
    public void removeFromBasket(Long itemId) {
        basket.removeIf(item -> item.getId().equals(itemId));
    }

    @Override
    public List<Item> getBasket() {
        return basket;
    }

    @Override
    public OrderDTO getOrder(Long id) {
        Order order = orderRepository.getOne(id);
        return new OrderDTO(order);
    }

    @Override
    public List<Item> getOrderItems(Long id) {
        Order order = orderRepository.getOne(id);
        return orderWaresEntityService.transformItemsFromIds(order.getItemList());
    }

    private Long generateOrderId(){
        return System.currentTimeMillis();
    }
}
