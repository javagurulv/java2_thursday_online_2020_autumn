package internet_store.application.core.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "shopping_cart_items")
public class ProductShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "shopping_cart_id", nullable = false)
    private ShoppingCart shoppingCart;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    @Column(name = "quantity", nullable = false)
    private int quantity;

    public ProductShoppingCart() {
    }

    public ProductShoppingCart(ShoppingCart shoppingCart, Product product, int quantity) {
        this.shoppingCart = shoppingCart;
        this.product = product;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductShoppingCart that = (ProductShoppingCart) o;
        return quantity == that.quantity && Objects.equals(id, that.id) && Objects.equals(shoppingCart, that.shoppingCart) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, shoppingCart, product, quantity);
    }

    @Override
    public String toString() {
        return "ProductShoppingCart{" +
                "id=" + id +
                ", shoppingCart=" + shoppingCart +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
