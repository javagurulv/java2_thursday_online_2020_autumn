package internet_store.application.core.responses.product;

import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.CoreResponse;

import java.util.List;

public class DeleteByProductNameResponse extends CoreResponse {

    private boolean productRemoved;

    public DeleteByProductNameResponse(List<CoreError> errors) {
        super(errors);
    }

    public DeleteByProductNameResponse(boolean productRemoved) {
        this.productRemoved = productRemoved;
    }

    public boolean isProductRemoved() {
        return productRemoved;
    }
}
