package internet_store.application.core.services.shopping_cart_item;

import internet_store.application.core.database.jpa.JpaShoppingCartItemRepository;
import internet_store.application.core.database.shopping_cart_item.ShoppingCartItemRepository;
import internet_store.application.core.domain.ProductShoppingCart;
import internet_store.application.core.requests.shopping_cart_item.FindShoppingCartItemByIdRequest;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.shopping_cart_item.FindShoppingCartItemByIdResponse;
import internet_store.application.core.services.shopping_cart_item.validators.FindShoppingCartItemByIdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FindShoppingCartItemByIdService {

    @Autowired private JpaShoppingCartItemRepository itemRepository;
    @Autowired private FindShoppingCartItemByIdValidator validator;

    public FindShoppingCartItemByIdResponse execute(FindShoppingCartItemByIdRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new FindShoppingCartItemByIdResponse(errors);
        }

        Optional<ProductShoppingCart> productShoppingCart = itemRepository.findById(request.getShoppingCartItemId());

        return new FindShoppingCartItemByIdResponse(productShoppingCart);
    }


}
