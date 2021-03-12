package internet_store.core.response.product.product_item;

import internet_store.core.core_error.CoreError;
import internet_store.core.core_error.CoreErrorResponse;

import java.util.List;

public class AddProductQuantityResponse extends CoreErrorResponse {
    public AddProductQuantityResponse(List<CoreError> errors) {
        super(errors);
    }
}