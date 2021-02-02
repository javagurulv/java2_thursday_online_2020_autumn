package internet_store.application.core.database.shopping_cart;

import internet_store.application.core.domain.ShoppingCart;

import java.util.List;

public interface ShoppingCartRepository {

    Long add(Long customerId);

    ShoppingCart findById(Long id);

    List<ShoppingCart> findAll();

}
