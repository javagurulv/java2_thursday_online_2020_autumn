package internet_store.application.console_ui;

import internet_store.application.core.domain.Order;
import internet_store.application.core.services.AddOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Scanner;

@Component
public class AddOrderUIAction implements UIAction {

    @Autowired AddOrderService addOrderService;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter order's customer ID: ");
        Long customerId = scanner.nextLong();
        System.out.println("Enter order's sum: ");
        BigDecimal orderSum = scanner.nextBigDecimal();
        Order order = new Order(customerId, orderSum);
        boolean result = addOrderService.execute(order);

        if (result) {
            System.out.println("Order has been added successfully!");
        } else {
            System.out.println("Order has not been added");
        }
    }

}
