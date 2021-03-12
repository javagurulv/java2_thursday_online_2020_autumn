package internet_store.application.core.services.shopping_cart_item;

import internet_store.application.core.database.jpa.JpaShoppingCartItemRepository;
import internet_store.application.core.database.shopping_cart_item.ShoppingCartItemRepository;
import internet_store.application.core.requests.shopping_cart_item.GetShoppingCartItemRequest;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.shopping_cart_item.GetShoppingCartItemResponse;
import internet_store.application.core.services.shopping_cart_item.validators.GetShoppingCartItemValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class GetShoppingCartItemService {

    @Autowired
    private JpaShoppingCartItemRepository shoppingCartItemRepository;
    @Autowired
    private GetShoppingCartItemValidator validator;

    public GetShoppingCartItemResponse execute(GetShoppingCartItemRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new GetShoppingCartItemResponse(errors);
        }
        return shoppingCartItemRepository.findById(request.getId())
                .map(GetShoppingCartItemResponse::new)
                .orElseGet(() -> {
                    errors.add(new CoreError("id", "Not found!"));
                    return new GetShoppingCartItemResponse(errors);
                });
    }

}
