package internet_store.application.console_ui.shopping_cart;

import internet_store.application.console_ui.UIAction;
import internet_store.application.core.requests.shopping_cart.AddShoppingCartRequest;
import internet_store.application.core.responses.shopping_cart.AddShoppingCartResponse;
import internet_store.application.core.services.shopping_cart.AddShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

//@Component
public class AddShoppingCartUIAction implements UIAction {

    @Autowired
    AddShoppingCartService addShoppingCartService;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter shopping cart's customer ID: ");
        Long customerId = scanner.nextLong();
        AddShoppingCartRequest request = new AddShoppingCartRequest(customerId);
        AddShoppingCartResponse response = addShoppingCartService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> {
                System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage());
            });
        } else {
            System.out.println("Shopping cart added to database: " + response.getNewShoppingCart());
        }
    }

}
