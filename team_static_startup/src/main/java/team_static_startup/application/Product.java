package team_static_startup.application;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {
    private Long id;
    private String productName;
    private String productDescription;
    private BigDecimal price;

    public Product(String productName, String productDescription, BigDecimal price) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return productName;
    }

    public void setName() {
        this.productName = productName;
    }

    public String getDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(productName, product.productName) &&
                Objects.equals(productDescription, product.productDescription) &&
                Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productName, productDescription, price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id = " + id +
                ", productName = '" + productName + '\'' +
                ", productDescription = '" + productDescription + '\'' +
                ", price = " + price +
                '}';
    }

}
