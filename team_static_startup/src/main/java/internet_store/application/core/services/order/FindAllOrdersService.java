package internet_store.application.core.services.order;

import internet_store.application.core.database.jpa.JpaOrderRepository;
import internet_store.application.core.database.order.OrderRepository;
import internet_store.application.core.responses.order.FindAllOrdersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FindAllOrdersService {

    @Autowired
    JpaOrderRepository orderRepository;

    public FindAllOrdersResponse execute() {
        return new FindAllOrdersResponse(orderRepository.findAll());
    }

}
