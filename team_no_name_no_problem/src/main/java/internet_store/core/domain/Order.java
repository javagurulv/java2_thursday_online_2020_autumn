package internet_store.core.domain;

import javax.persistence.*;
import java.util.Map;
import java.util.Objects;


public class Order {

    private Long id;

    private Customer customer;

    private Map<Product, Integer> shoppingCart;

    private Integer sum;

    public Order(Customer customer, Map<Product, Integer> shoppingCart, Integer sum) {
        this.customer = customer;
        this.shoppingCart = shoppingCart;
        this.sum = sum;
    }

    public Order() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<Product, Integer> getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(Map<Product, Integer> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(shoppingCart, order.shoppingCart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, shoppingCart);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", shoppingCart=" + shoppingCart +
                '}';
    }
}
