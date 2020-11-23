package internet_store.core.services.shopping_cart;

import internet_store.core.domain.ShoppingCart;

public class AddToShoppingCartService {

    private final ShoppingCart shoppingCart;

    public AddToShoppingCartService(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
}
