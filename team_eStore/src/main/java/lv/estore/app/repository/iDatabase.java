package lv.estore.app.repository;

import lv.estore.app.domain.Product;

import java.math.BigDecimal;
import java.util.List;

public interface iDatabase {

    boolean addProduct(final Product product);
    boolean updateById(final Long id, final String name, final String description, final BigDecimal price);
    boolean removeById(final Long id);
    boolean removeByName(final String name);
    Product findById(final Long id);
    Product findByName(final String name);
    List<Product> searchByName(final String name);
    List<Product> getAllProducts();
}
