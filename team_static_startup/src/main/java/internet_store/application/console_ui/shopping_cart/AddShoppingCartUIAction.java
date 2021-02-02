package internet_store.application.console_ui.shopping_cart;

import internet_store.application.console_ui.UIAction;
import internet_store.application.core.database.shopping_cart.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AddShoppingCartUIAction implements UIAction {

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter customer id: ");
        Long customerId = scanner.nextLong();
        Long shoppingCartId = shoppingCartRepository.add(customerId);

        System.out.println("Shopping cart added with ID: " + shoppingCartId);
    }

}
