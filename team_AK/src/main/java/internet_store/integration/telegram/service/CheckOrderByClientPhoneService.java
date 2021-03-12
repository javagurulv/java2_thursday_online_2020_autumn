package internet_store.integration.telegram.service;

import internet_store.core.persistence.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckOrderByClientPhoneService {
    @Autowired
    private OrderRepository orderRepository;

    public boolean isClientPhoneExists(String phoneNumber) {
        return orderRepository.existsByClientPhoneNumber(phoneNumber);
    }
}