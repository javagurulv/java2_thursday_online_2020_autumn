package internet_store.application.console_ui.order;

import internet_store.application.console_ui.UIAction;
import internet_store.application.core.database.order.OrderRepository;
import internet_store.application.core.domain.Order;
import internet_store.application.core.domain.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class FindOrderByIdUIAction implements UIAction {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter order ID: ");
        Long orderId = scanner.nextLong();
        Order foundOrder = orderRepository.findById(orderId);
        System.out.println(foundOrder);
    }
}
