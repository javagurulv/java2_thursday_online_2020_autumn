package internet_store.core.request.product.product_items;

import lombok.Getter;

public class AddProductCategoryRequest {
    @Getter
    private final Integer category;

    public AddProductCategoryRequest(Integer category) {
        this.category = category;
    }
}