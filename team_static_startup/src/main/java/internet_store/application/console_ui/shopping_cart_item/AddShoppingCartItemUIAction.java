package internet_store.application.console_ui.shopping_cart_item;

import internet_store.application.console_ui.UIAction;
import internet_store.application.core.database.shopping_cart_item.ShoppingCartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AddShoppingCartItemUIAction implements UIAction {

    @Autowired
    ShoppingCartItemRepository shoppingCartItemRepository;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter shopping cart ID: ");
        Long shoppingCartId = scanner.nextLong();
        System.out.println("Enter product ID: ");
        Long productId = scanner.nextLong();
        System.out.println("Enter product quantity: ");
        int quantity = scanner.nextInt();

        Long shoppingCartItemId = shoppingCartItemRepository.add(shoppingCartId, productId, quantity);
        System.out.println("Shopping cart item added with ID: " + shoppingCartItemId);
    }

}
