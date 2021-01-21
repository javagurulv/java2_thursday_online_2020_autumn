package internet_store.application.core.services;

import internet_store.application.core.database.OrderDatabase;
import internet_store.application.core.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllOrdersService {

    @Autowired OrderDatabase orderDatabase;

    public List<Order> execute() {
        return orderDatabase.findAll();
    }

}
