package internet_store.application.console_ui.order;

import internet_store.application.console_ui.UIAction;
import internet_store.application.core.database.order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AddOrderUIAction implements UIAction {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter shopping cart ID: ");
        Long shoppingCartId = scanner.nextLong();

        Long orderId = orderRepository.add(shoppingCartId);
        System.out.println("Order added with ID: " + orderId);
    }

}
