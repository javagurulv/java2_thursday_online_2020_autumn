package lv.estore.app.core.responses;

import lv.estore.app.core.errors.CoreError;

import java.util.List;

public class UpdateResponse extends CoreResponse{
    private boolean productUpdated;

    public UpdateResponse(boolean productUpdated) {
        this.productUpdated = productUpdated;
    }

    public UpdateResponse(List<CoreError> errors) {
        super(errors);
    }

    public boolean isProductUpdated() {
        return productUpdated;
    }
}
