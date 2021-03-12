package internet_store.application.core.services.shopping_cart_item;

import internet_store.application.core.database.jpa.JpaProductRepository;
import internet_store.application.core.database.jpa.JpaShoppingCartItemRepository;
import internet_store.application.core.database.jpa.JpaShoppingCartRepository;
import internet_store.application.core.domain.Product;
import internet_store.application.core.domain.ProductShoppingCart;
import internet_store.application.core.domain.ShoppingCart;
import internet_store.application.core.requests.shopping_cart_item.AddShoppingCartItemRequest;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.shopping_cart_item.AddShoppingCartItemResponse;
import internet_store.application.core.services.shopping_cart_item.validators.AddShoppingCartItemValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class AddShoppingCartItemService {

    @Autowired private JpaShoppingCartItemRepository itemRepository;
    @Autowired private JpaShoppingCartRepository cartRepository;
    @Autowired private JpaProductRepository productRepository;
    @Autowired private AddShoppingCartItemValidator validator;

    public AddShoppingCartItemResponse execute(AddShoppingCartItemRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddShoppingCartItemResponse(errors);
        }

        Optional<ShoppingCart> shoppingCart = cartRepository.findById(request.getShoppingCartId());
        Optional<Product> product = productRepository.findById(request.getProductId());

        if (shoppingCart.isEmpty()) {
            return errorIfIdNotFound(errors, "Shopping Cart ID");
        } else if (product.isEmpty()) {
            return errorIfIdNotFound(errors, "Product ID");
        }

        ProductShoppingCart savedCartIem = itemRepository
                .saveAndFlush(new ProductShoppingCart(shoppingCart.get(), product.get(), request.getQuantity()));

        return new AddShoppingCartItemResponse(savedCartIem.getId());
    }

    private AddShoppingCartItemResponse errorIfIdNotFound(List<CoreError> errors, String errorMessage) {
        errors.add(new CoreError(errorMessage, "Not found!"));
        return new AddShoppingCartItemResponse(errors);
    }

}
