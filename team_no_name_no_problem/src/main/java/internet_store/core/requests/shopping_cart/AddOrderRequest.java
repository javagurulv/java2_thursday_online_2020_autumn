package internet_store.core.requests.shopping_cart;

import internet_store.core.domain.Customer;
import internet_store.core.domain.ShoppingCart;

public class AddOrderRequest {

    private Customer customer;
    private ShoppingCart shoppingCart;

    public AddOrderRequest (Customer customer,ShoppingCart shoppingCart) {
        this.customer = customer;
        this.shoppingCart = shoppingCart;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public Customer getCustomer() {
        return customer;
    }

}
