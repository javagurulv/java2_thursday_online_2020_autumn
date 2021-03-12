package internet_store.integration.telegram.service;

import internet_store.core.persistence.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckOrderByOrderNumberService {
    @Autowired
    private OrderRepository orderRepository;

    public boolean isOrderExists(String orderNumber) {
        return orderRepository.existsByNumber(orderNumber);
    }
}