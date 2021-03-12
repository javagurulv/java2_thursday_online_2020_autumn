package internet_store.integration.telegram.service;

import internet_store.core.domain.Order;
import internet_store.core.persistence.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FindOrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Optional<Order> tryFindOrder(String orderNumber) {
        List<Order> orders = orderRepository.findAllByNumber(orderNumber);
        if (orders.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(orders.get(0));
    }
}