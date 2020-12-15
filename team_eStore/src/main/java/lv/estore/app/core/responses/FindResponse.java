package lv.estore.app.core.responses;

import lv.estore.app.domain.Product;
import lv.estore.app.core.errors.Errors;

public class FindResponse extends CoreResponse{
    private Product product;

    public FindResponse(final Errors errors) {
        super(errors);
    }

    public FindResponse(final Product product) {
        this.product = product;
    }

    public Product productFound(){
        return product;
    }

    @Override
    public String toString() {
        return "Found " + productFound().toString();
    }
}
