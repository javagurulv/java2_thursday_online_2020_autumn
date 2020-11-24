package internet_store.core.response.shopping_cart;

import internet_store.core.domain.ShoppingCart;
import internet_store.core.response.CoreError;
import internet_store.core.response.CoreResponse;

import java.util.List;

public class AddShoppingCartResponse extends CoreResponse {

    private ShoppingCart shoppingCart;

    public AddShoppingCartResponse(List<CoreError> errors) {super(errors);}

    public AddShoppingCartResponse(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

}
