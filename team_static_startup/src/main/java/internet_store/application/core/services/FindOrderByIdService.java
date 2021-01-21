package internet_store.application.core.services;

import internet_store.application.core.database.OrderDatabase;
import internet_store.application.core.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FindOrderByIdService {

    @Autowired OrderDatabase orderDatabase;

    public Order execute(Long id) {
        return orderDatabase.findById(id).get();
    }

}
