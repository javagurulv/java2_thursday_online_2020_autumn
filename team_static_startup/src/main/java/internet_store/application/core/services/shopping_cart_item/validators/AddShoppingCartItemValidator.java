package internet_store.application.core.services.shopping_cart_item.validators;

import internet_store.application.core.requests.shopping_cart_item.AddShoppingCartItemRequest;
import internet_store.application.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AddShoppingCartItemValidator {

    public List<CoreError> validate(AddShoppingCartItemRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateShoppingCartId(request).ifPresent(errors::add);
        validateProductId(request).ifPresent(errors::add);
        validateQuantity(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateShoppingCartId(AddShoppingCartItemRequest request) {
        return (request.getShoppingCartId() == null || request.getShoppingCartId() <=0
                ? Optional.of(new CoreError("Shopping Cart Id.", "Should be grater than zero or not equal to NULL."))
                : Optional.empty());
    }

    private Optional<CoreError> validateProductId(AddShoppingCartItemRequest request) {
        return (request.getProductId() == null || request.getProductId() <=0
                ? Optional.of(new CoreError("Product Id.", "Should be grater than zero or not equal to NULL."))
                : Optional.empty());
    }

    private Optional<CoreError> validateQuantity(AddShoppingCartItemRequest request) {
        return (request.getQuantity() == null || request.getQuantity() <=0
                ? Optional.of(new CoreError("Quantity.", "Should be grater than zero."))
                : Optional.empty());
    }

}
