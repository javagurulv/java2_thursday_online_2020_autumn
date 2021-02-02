package internet_store.application.console_ui.order;

import internet_store.application.console_ui.UIAction;
import internet_store.application.core.database.order.OrderRepository;
import internet_store.application.core.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindAllOrdersUIAction implements UIAction {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public void execute() {
        List<Order> orders = orderRepository.findAll();
        orders.forEach(System.out::println);
    }

}
