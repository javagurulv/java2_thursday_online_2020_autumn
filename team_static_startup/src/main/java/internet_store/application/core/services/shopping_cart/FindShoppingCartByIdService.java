package internet_store.application.core.services.shopping_cart;

import internet_store.application.core.database.jpa.JpaShoppingCartRepository;
import internet_store.application.core.domain.ShoppingCart;
import internet_store.application.core.requests.shopping_cart.FindShoppingCartByIdRequest;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.shopping_cart.FindShoppingCartByIdResponse;
import internet_store.application.core.services.shopping_cart.validators.FindShoppingCartByIdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FindShoppingCartByIdService {

    @Autowired JpaShoppingCartRepository shoppingCartRepository;
    @Autowired FindShoppingCartByIdValidator validator;

    public FindShoppingCartByIdResponse execute(FindShoppingCartByIdRequest request) {
        List<CoreError> errors = validator.validate(request);

        if (!errors.isEmpty()) {
            return new FindShoppingCartByIdResponse(errors);
        }

        Optional<ShoppingCart> shoppingCart = shoppingCartRepository.findById(request.getId());

        if (shoppingCart.isEmpty()) {
            errors.add(new CoreError("id", "Not found!"));
            return new FindShoppingCartByIdResponse(errors);
        }

        return new FindShoppingCartByIdResponse(shoppingCart.get());
    }

}
