package internet_store.application.core.requests.product;

import java.math.BigDecimal;

public class DeleteByProductRequest {

    private String productName;
    private String productDescription;
    private BigDecimal productPrice;

    public DeleteByProductRequest(String productName, String productDescription, BigDecimal productPrice) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }
}
