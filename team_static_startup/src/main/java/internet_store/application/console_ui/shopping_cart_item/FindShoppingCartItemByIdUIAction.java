package internet_store.application.console_ui.shopping_cart_item;

import internet_store.application.console_ui.UIAction;
import internet_store.application.core.database.shopping_cart_item.ShoppingCartItemRepository;
import internet_store.application.core.domain.ProductShoppingCart;
import internet_store.application.core.dto.ShoppingCartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Scanner;

@Component
public class FindShoppingCartItemByIdUIAction implements UIAction {

    @Autowired
    ShoppingCartItemRepository shoppingCartItemRepository;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter shopping cart item ID: ");
        Long shoppingCartItemId = scanner.nextLong();
        ProductShoppingCart productShoppingCart = shoppingCartItemRepository.findById(shoppingCartItemId);

        Long id = productShoppingCart.getId();
        Long shoppingCartId = productShoppingCart.getShoppingCart().getId();
        Long productId = productShoppingCart.getProduct().getId();
        String productName = productShoppingCart.getProduct().getName();
        BigDecimal productPrice = productShoppingCart.getProduct().getPrice();
        int quantity = productShoppingCart.getQuantity();

        ShoppingCartItem shoppingCartItem = new ShoppingCartItem(id, shoppingCartId, productId, productName, productPrice, quantity);
        System.out.println(shoppingCartItem);
    }

}
