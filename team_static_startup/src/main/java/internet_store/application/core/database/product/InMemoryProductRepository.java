package internet_store.application.core.database.product;

import internet_store.application.core.domain.Product;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//@Component
//@Profile("inmemory")
public class InMemoryProductRepository implements ProductRepository {
    private Long id = 1L;
    private final List<Product> productList = new ArrayList<>();

    @Override
    public Long add(Product product) {
        product.setId(id);
        productList.add(product);
        return ++id;
    }

    @Override
    public boolean deleteByProductId(Long productIdLong) {
        for (Product product : productList) {
            if (product.getId().equals(productIdLong)) {
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
    public boolean deleteByProductName(String productName) {
        return productList.removeIf(products -> (products.getName().equals(productName)));
    }

    @Override
    public List<Product> getProductList() {
        return new ArrayList<>(productList);
    }

    @Override
    public List<Product> findByProductName(String productName) {
        return productList.stream()
                .filter(productInDataBase -> productInDataBase.getName().equalsIgnoreCase(productName))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findByProductDescription(String productDescription) {
        return productList.stream()
                .filter(productInDataBase -> productInDataBase.getDescription().equalsIgnoreCase(productDescription))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findByNameAndDescription(String name, String description) {
        return productList.stream()
                .filter(product -> product.getDescription().equals(description))
                .filter(product -> product.getName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Product> findById(Long productId) {
        return productList.stream()
                .filter(productInDataBase -> productInDataBase.getId().equals(productId))
                .findFirst();
    }

    @Override
    public boolean changeProductName(Long productId, String newName) {
        for (Product product : productList) {
            if (product.getId().equals(productId)) {
                product.setName(newName);
                return true;
            }
        }
        return false;
    }

}
