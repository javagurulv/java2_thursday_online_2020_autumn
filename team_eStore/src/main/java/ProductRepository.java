import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductRepository {

    private static ProductRepository productRepository;
    public static List<Product> productList;
    /**
     * Class constructor, initialize product list with default products
     */
    private ProductRepository (){
        initializeProductList();
    }

    public static ProductRepository getProductRepository() {
        if (productRepository == null){
            return new ProductRepository();
        } else {
            return productRepository;
        }
    }

    /**
     * Method to initialize product list with default products
     */
    public void initializeProductList(){
        productList = new ArrayList<>();
        Product productOne = new Product("laptop", "standart laptop with 15\" display", 800.0);
        Product productTwo = new Product("mouse", "wireless mouse", 20.0);
        Product productThree = new Product("keyboard", "wireless keyboard", 40.0);
        productList.add(productOne);
        productList.add(productTwo);
        productList.add(productThree);
    }

    /**
     * Method to add product to the store
     */
    public void addProduct(final String name, final String description, final double price){
        Product product = new Product(name, description, price);
        productList.add(product);
        System.out.println("Product added.");
    }

    /**
     * Method to remove product form the store by ID
     */
    public void removeProduct(final String name){
        Product productToRemove = null;
        for (Product product : productList) {
            if (name.equals(product.getName())) {
                productToRemove = product;
            }
        }
        if (productToRemove == null) {
            System.out.println("Product not found.");
        } else {
            productList.remove(productToRemove);
            System.out.println("Product removed form the store.");
        }
    }

    /**
     * Method to find product by the name
     */
    public Product findProductByName(final String name) {
        Product foundProduct = null;
        for (Product product : productList) {
            if (name.equals(product.getName())) {
                foundProduct = product;
            }
        }
        if (foundProduct == null) {
            System.out.println("Product not found.");
        }
        return foundProduct;
    }

    /**
     * Method to print out all the list of products form the store
     */
    public void printOutAllProducts(){
        if (productList.isEmpty()) {
            System.out.println("Store is empty now.");
        } else {
            Collections.sort(productList, Product.idComparator);
            for (Product product : productList) {
                System.out.println(product);
            }
        }
    }

    /**
     * Method to update product info
     */
    public void updateProduct(final String name,
                              final String newName,
                              final String newDescription,
                              final double newPrice){
        Product foundProduct = findProductByName(name);
        productList.remove(foundProduct);
        foundProduct.setName(newName);
        foundProduct.setDescription(newDescription);
        foundProduct.setPrice(newPrice);
        productList.add(foundProduct);
    }
}
