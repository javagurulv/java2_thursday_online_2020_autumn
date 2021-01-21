package internet_store.application.core.services;

import internet_store.application.core.database.OrderDatabase;
import internet_store.application.core.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddOrderService {

    @Autowired private OrderDatabase orderDatabase;

    public boolean execute(Order order) {
        return orderDatabase.add(order) > 0;
    }

}
