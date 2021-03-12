package internet_store.application.core.services.shopping_cart_item;


import internet_store.application.core.database.jpa.JpaShoppingCartItemRepository;
import internet_store.application.core.database.shopping_cart_item.ShoppingCartItemRepository;
import internet_store.application.core.requests.shopping_cart_item.DeleteShoppingCartItemRequest;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.shopping_cart_item.DeleteShoppingCartItemResponse;
import internet_store.application.core.services.shopping_cart_item.validators.DeleteShoppingCartItemValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class DeleteShoppingCartItemService {

    @Autowired private JpaShoppingCartItemRepository shoppingCartItemRepository;
    @Autowired private DeleteShoppingCartItemValidator validator;

    public DeleteShoppingCartItemResponse execute(DeleteShoppingCartItemRequest request){
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new DeleteShoppingCartItemResponse(errors);
        }
        return shoppingCartItemRepository.findById(request.getId())
                .map(product -> {
                    shoppingCartItemRepository.deleteById(request.getId());
                    return new DeleteShoppingCartItemResponse(product);
                })
                .orElseGet(() -> {
                    errors.add(new CoreError("id", "Nor found!"));
                    return new DeleteShoppingCartItemResponse(errors);
                });
    }
}
