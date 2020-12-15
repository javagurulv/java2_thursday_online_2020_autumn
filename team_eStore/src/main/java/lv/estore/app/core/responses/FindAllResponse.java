package lv.estore.app.core.responses;

import lv.estore.app.domain.Product;
import lv.estore.app.core.errors.Errors;

import java.util.List;

public class FindAllResponse extends CoreResponse{
    private List<Product> products;

    public FindAllResponse(Errors errors) {
        super(errors);
    }

    public FindAllResponse(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Product p : getProducts()) {
            sb.append(p.toString()).append("\n");
        }
        return sb.toString();
    }
}
