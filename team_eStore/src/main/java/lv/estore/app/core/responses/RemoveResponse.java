package lv.estore.app.core.responses;

import lv.estore.app.core.errors.Errors;

public class RemoveResponse extends CoreResponse {
    private boolean productRemoved;

    public RemoveResponse(boolean productRemoved) {
        this.productRemoved = productRemoved;
    }

    public RemoveResponse(Errors errors) {
        super(errors);
    }

    public boolean isProductRemoved() {
        return productRemoved;
    }

    @Override
    public String toString() {
        return "Product is removed = " + isProductRemoved();
    }
}
