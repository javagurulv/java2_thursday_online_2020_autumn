package internet_store.application.core.services.order;

import internet_store.application.core.database.jpa.JpaOrderRepository;
import internet_store.application.core.database.jpa.JpaShoppingCartRepository;
import internet_store.application.core.database.order.OrderRepository;
import internet_store.application.core.database.shopping_cart.ShoppingCartRepository;
import internet_store.application.core.domain.Order;
import internet_store.application.core.domain.ShoppingCart;
import internet_store.application.core.requests.order.AddOrderRequest;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.order.AddOrderResponse;
import internet_store.application.core.services.order.validators.AddOrderValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Transactional
public class AddOrderService {

    @Autowired
    JpaOrderRepository orderRepository;
    @Autowired
    JpaShoppingCartRepository shoppingCartRepository;
    @Autowired
    AddOrderValidator validator;

    public AddOrderResponse execute(AddOrderRequest request) {
        List<CoreError> errors = validator.validate(request);

        if (!errors.isEmpty()) {
            return new AddOrderResponse(errors);
        }
        ShoppingCart shoppingCart = shoppingCartRepository.findById(request.getShoppingCartId()).get();

        Order order = new Order(shoppingCart);
        order.setOrderDate(LocalDateTime.now());
        order.setActive(true);
        // order.setOrderDate(request.getOrderDate());
        // order.setActive(request.isActive());
        orderRepository.save(order);
        return new AddOrderResponse(order);
    }

}
