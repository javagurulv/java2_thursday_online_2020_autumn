package internet_store.application.core.dto;

import java.math.BigDecimal;

public class ShoppingCartItem {

    private Long id;
    private Long shoppingCartId;
    private Long productId;
    private String productName;
    private BigDecimal productPrice;
    private int quantity;

    public ShoppingCartItem(Long id, Long shoppingCartId, Long productId, String productName, BigDecimal productPrice, int quantity) {
        this.id = id;
        this.shoppingCartId = shoppingCartId;
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.quantity = quantity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setShoppingCartId(Long shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ShoppingCartItem{" +
                "id=" + id +
                ", shoppingCartId=" + shoppingCartId +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", quantity=" + quantity +
                '}';
    }
}
