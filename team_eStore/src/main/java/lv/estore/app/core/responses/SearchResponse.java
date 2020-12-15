package lv.estore.app.core.responses;

import lv.estore.app.core.errors.Errors;
import lv.estore.app.domain.Product;

import java.util.List;

public class SearchResponse extends CoreResponse{
    private List<Product> products;

    public SearchResponse(final Errors errors) {
        super(errors);
    }

    public SearchResponse(final List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }
}
