package team_static_startup.application;

import java.util.*;

public class ProductDatabaseImpl implements ProductDatabase {
    private Long id = 1L;
    private final List<Product> productList = new ArrayList<>();

    @Override
    public Long save(Product product) {
        product.setId(id);
        productList.add(product);
        return ++id;
    }

    @Override
    public boolean delete(Long productId) {
        for (Product product : productList) {
            if (product.getId().equals(productId)) {
                productList.remove(product);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(Product product) {
        for (Product products : productList) {
            if ((products.getName().equals(product.getName())) && (products.getDescription().equals(product.getDescription()))) {
                productList.remove(product);
                return true;
            }
        }
        return false;
    }

    @Override
    public void deleteByProductName(String productName) {
        productList.removeIf(products -> (products.getName().equals(productName)));
    }

}
