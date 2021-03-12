package internet_store.core.request.product.product_items;

import lombok.Getter;

public class AddProductDescriptionRequest {
    @Getter
    private final String productDescription;

    public AddProductDescriptionRequest(String productDescription) {
        this.productDescription = productDescription;
    }
}