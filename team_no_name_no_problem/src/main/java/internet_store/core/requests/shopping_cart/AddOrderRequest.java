package internet_store.core.requests.shopping_cart;

import internet_store.core.domain.ShoppingCart;

public class AddOrderRequest {

    private ShoppingCart shoppingCart;

    public AddOrderRequest (ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }
}
