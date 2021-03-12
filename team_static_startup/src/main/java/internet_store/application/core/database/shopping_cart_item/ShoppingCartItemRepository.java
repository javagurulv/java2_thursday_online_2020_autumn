package internet_store.application.core.database.shopping_cart_item;

import internet_store.application.core.domain.ProductShoppingCart;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartItemRepository {

    Long add(Long shoppingCartId, Long productId, Long quantity);

    Optional<ProductShoppingCart> findById(Long id);

    List<ProductShoppingCart> findAll();

    boolean deleteById(Long id);

}