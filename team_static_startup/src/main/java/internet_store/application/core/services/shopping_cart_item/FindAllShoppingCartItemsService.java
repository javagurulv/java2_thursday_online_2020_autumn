package internet_store.application.core.services.shopping_cart_item;

import internet_store.application.core.database.jpa.JpaShoppingCartItemRepository;
import internet_store.application.core.database.shopping_cart_item.ShoppingCartItemRepository;
import internet_store.application.core.domain.ProductShoppingCart;
import internet_store.application.core.requests.shopping_cart_item.FindAllShoppingCartItemsRequest;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.shopping_cart_item.FindAllShoppingCartItemsResponse;
import internet_store.application.core.services.shopping_cart_item.validators.FindAllShoppingCartItemsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindAllShoppingCartItemsService {

    @Autowired
    private JpaShoppingCartItemRepository itemRepository;
    @Autowired private FindAllShoppingCartItemsValidator validator;

    public FindAllShoppingCartItemsResponse execute(FindAllShoppingCartItemsRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new FindAllShoppingCartItemsResponse(errors, null);
        }

        List<ProductShoppingCart> productShoppingCartList= itemRepository.findAll();

        return new FindAllShoppingCartItemsResponse(null, productShoppingCartList);
    }

}
