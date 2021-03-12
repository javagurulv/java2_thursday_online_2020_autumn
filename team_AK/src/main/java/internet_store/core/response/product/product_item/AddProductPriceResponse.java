package internet_store.core.response.product.product_item;

import internet_store.core.core_error.CoreError;
import internet_store.core.core_error.CoreErrorResponse;

import java.util.List;

public class AddProductPriceResponse extends CoreErrorResponse {

    public AddProductPriceResponse(List<CoreError> errors) {
        super(errors);
    }
}