package internet_store.application.core.services.shopping_cart_item;

import internet_store.application.core.database.jpa.JpaShoppingCartItemRepository;
import internet_store.application.core.database.shopping_cart_item.ShoppingCartItemRepository;
import internet_store.application.core.requests.shopping_cart_item.UpdateShoppingCartItemRequest;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.shopping_cart_item.UpdateShoppingCartItemResponse;
import internet_store.application.core.services.shopping_cart_item.validators.UpdateShoppingCartItemValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class UpdateShoppingCartItemService {

    @Autowired private JpaShoppingCartItemRepository shoppingCartItemRepository;
    @Autowired private UpdateShoppingCartItemValidator validator;

    public UpdateShoppingCartItemResponse execute(UpdateShoppingCartItemRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new UpdateShoppingCartItemResponse(errors);
        }

        return shoppingCartItemRepository.findById(request.getId())
                .map(cartItem -> {
                    cartItem.setQuantity(request.getQuantity());
                    return new UpdateShoppingCartItemResponse(cartItem);
                })
                .orElseGet(() -> {
                    errors.add(new CoreError("id", "Not fount!"));
                    return new UpdateShoppingCartItemResponse(errors);
                });

    }
}
