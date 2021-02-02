package internet_store.application.console_ui.shopping_cart_item;

import internet_store.application.console_ui.UIAction;
import internet_store.application.core.database.product.ProductRepository;
import internet_store.application.core.database.shopping_cart_item.ShoppingCartItemRepository;
import internet_store.application.core.domain.Product;
import internet_store.application.core.domain.ProductShoppingCart;
import internet_store.application.core.dto.ShoppingCartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class FindAllShoppingCartItemsUIAction implements UIAction {

    @Autowired
    ShoppingCartItemRepository shoppingCartItemRepository;
    @Autowired
    ProductRepository productRepository;

    @Override
    public void execute() {
        List<ProductShoppingCart> productShoppingCarts = shoppingCartItemRepository.findAll();
        List<ShoppingCartItem> shoppingCartItems = new ArrayList<>();
        productShoppingCarts.forEach(productShoppingCart -> {
            Long id = productShoppingCart.getId();
            Long shoppingCartId = productShoppingCart.getShoppingCart().getId();
            Long productId = productShoppingCart.getProduct().getId();
            String productName = productShoppingCart.getProduct().getName();
            BigDecimal productPrice = productShoppingCart.getProduct().getPrice();
            int quantity = productShoppingCart.getQuantity();

            ShoppingCartItem shoppingCartItem = new ShoppingCartItem(id, shoppingCartId, productId, productName, productPrice, quantity);
            shoppingCartItems.add(shoppingCartItem);
        });
        shoppingCartItems.forEach(System.out::println);
    }

}
