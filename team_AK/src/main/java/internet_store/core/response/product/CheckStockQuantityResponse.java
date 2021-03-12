package internet_store.core.response.product;

import internet_store.core.core_error.CoreError;
import internet_store.core.core_error.CoreErrorResponse;

import java.util.List;

public class CheckStockQuantityResponse extends CoreErrorResponse {
    public CheckStockQuantityResponse(List<CoreError> errors) {
        super(errors);
    }
}