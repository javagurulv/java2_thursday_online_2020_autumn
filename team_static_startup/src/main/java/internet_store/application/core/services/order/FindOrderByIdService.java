package internet_store.application.core.services.order;

import internet_store.application.core.database.jpa.JpaOrderRepository;
import internet_store.application.core.database.order.OrderRepository;
import internet_store.application.core.domain.Order;
import internet_store.application.core.requests.order.FindOrderByIdRequest;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.order.FindOrderByIdResponse;
import internet_store.application.core.services.order.validators.FindOrderByIdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindOrderByIdService {

    @Autowired
    JpaOrderRepository orderRepository;
    @Autowired
    FindOrderByIdValidator validator;

    public FindOrderByIdResponse execute(FindOrderByIdRequest request) {
        List<CoreError> errors = validator.validate(request);

        if (!errors.isEmpty()) {
            return new FindOrderByIdResponse(errors);
        }

        Order order = orderRepository.findById(request.getId()).get();
        return new FindOrderByIdResponse(order);
    }

}
