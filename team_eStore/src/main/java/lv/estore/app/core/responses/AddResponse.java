package lv.estore.app.core.responses;

import lv.estore.app.core.errors.Errors;

public class AddResponse extends CoreResponse {
    private boolean isAdded;

    public AddResponse(Errors errors) {
        super(errors);
    }

    public AddResponse(boolean isAdded) {
        this.isAdded = isAdded;
    }

    public boolean isProductAdded(){
        return isAdded;
    }

    @Override
    public String toString() {
        return "Product is added = " + isAdded;
    }
}
