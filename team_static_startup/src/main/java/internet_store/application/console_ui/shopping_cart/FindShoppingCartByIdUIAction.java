package internet_store.application.console_ui.shopping_cart;

import internet_store.application.console_ui.UIAction;
import internet_store.application.core.database.shopping_cart.ShoppingCartRepository;
import internet_store.application.core.domain.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class FindShoppingCartByIdUIAction implements UIAction {

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter shopping cart ID: ");
        Long shoppingCartId = scanner.nextLong();
        ShoppingCart foundShoppingCart = shoppingCartRepository.findById(shoppingCartId);
        System.out.println(foundShoppingCart);
    }
}
