package lv.estore.app.repository;

import lv.estore.app.dependency_injection.DIComponent;
import lv.estore.app.domain.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@DIComponent
public class ProductDatabase implements iDatabase {

    private List<Product> productList = new ArrayList<>();
    private Long id = 1L;
    private static ProductDatabase productDataBase;

    /**
     * Class constructor, initialize product list with default products.
     */
    private ProductDatabase(){
    }

    /**
     * Method to return ProductDataBase instance.
     */
    public static ProductDatabase getDataBase() {
        if (productDataBase == null){
            productDataBase = new ProductDatabase();
        }
        return productDataBase;
    }

    /**
     * Method to add product.
     * @param product Product instance.
     * @return true if product was added.
     */
    @Override
    public boolean addProduct(final Product product) {
        product.setId(id);
        id++;
        return productList.add(product);
    }

    /**
     * Method to update product by product 'Id'.
     * @param id Long 'id' of product to find.
     * @param newName String 'newName' updated name.
     * @param newDescription String 'newDescription' updated description.
     * @param newPrice String 'newPrice' updated price.
     * @return true if product was updated.
     */
    @Override
    public boolean updateById(final Long id, final String newName, final String newDescription, final BigDecimal newPrice) {
        Product product = null;
        for (Product p : getAllProducts()) {
            if (id == p.getId()) {
                product = p;
            }
        }
        if (product != null) {
            product.setName(newName);
            product.setDescription(newDescription);
            product.setPrice(newPrice);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method to remove product by 'Id'.
     * @param id Long 'id' of product to remove.
     * @return true if product was removed.
     */
    @Override
    public boolean removeById(final Long id) {
        return getAllProducts().removeIf(product -> id.equals(product.getId()));
    }

    /**
     * Method to remove product by 'Name'.
     * @param name String 'name' of product to remove.
     * @return true if product was removed.
     */
    @Override
    public boolean removeByName(final String name) {
        return getAllProducts().removeIf(product -> name.equals(product.getName()));
    }

    /**
     * Method to find product by 'Id'.
     * @param id Long 'id' of product to find.
     * @return Product if product was found, else null.
     */
    @Override
    public Product findById(final Long id) {
        return getAllProducts()
                .stream()
                .filter(product -> id == product.getId())
                .findFirst()
                .orElse(null);
    }

    /**
     * Method to find product by 'Name'.
     * @param name String 'name' of product to find.
     * @return Product if product was found, else null.
     */
    @Override
    public Product findByName(final String name) {
        return getAllProducts()
                .stream()
                .filter(product -> name.equals(product.getName()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Product> searchByName(final String name) {
        return getAllProducts()
                .stream()
                .filter(p -> p.getName().contains(name))
                .collect(Collectors.toList());
    }

    /**
     * Method returns all products.
     * @return List<Product> list of stored products.
     */
    @Override
    public List<Product> getAllProducts() {
        return productList;
    }
}
