package internet_store.application.console_ui.order;

import internet_store.application.console_ui.UIAction;
import internet_store.application.core.requests.order.AddOrderRequest;
import internet_store.application.core.responses.order.AddOrderResponse;
import internet_store.application.core.services.order.AddOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

//@Component
public class AddOrderUIAction implements UIAction {

    @Autowired
    AddOrderService addOrderService;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter order's shopping cart ID: ");
        Long shoppingCartId = scanner.nextLong();

        AddOrderRequest request = new AddOrderRequest(shoppingCartId);
        request.setOrderDate(LocalDateTime.now());
        request.setActive(true);
        AddOrderResponse response = addOrderService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> {
                System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage());
            });
        } else {
            System.out.println("Order added to database: " + response.getNewOrder());
        }
    }

}
