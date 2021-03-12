package internet_store.core.response.product.product_item;

import internet_store.core.core_error.CoreError;
import internet_store.core.core_error.CoreErrorResponse;
import lombok.Getter;

import java.util.List;

public class AddProductDescriptionResponse extends CoreErrorResponse {
    @Getter
    private String productDescription;

    public AddProductDescriptionResponse(List<CoreError> errors) {
        super(errors);
    }

    public AddProductDescriptionResponse(String productDescription) {
        this.productDescription = productDescription;
    }
}