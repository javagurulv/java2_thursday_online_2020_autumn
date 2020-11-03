package team_static_startup.application;

import java.util.List;

public interface ProductDatabase {

    Long save(Product product);

    boolean delete(Long productId);

    boolean delete(Product product);

    void deleteByProductName(String product);

    List<Product> findByProductName(String productName);

    List<Product> getProductList();

}
