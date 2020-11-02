package internet_store;

import internet_store.domain.Product;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class ProductDatabaseImplTest {

    @Test
    public void saveProductTest() {
        ProductDatabaseImpl productDatabase = new ProductDatabaseImpl();

        Product firstProduct = new Product("Laptop","Dell",400);
        Product secondProduct = new Product("Refrigerator","Electrolux",300);
        boolean saveResult1 = productDatabase.save(firstProduct);
        productDatabase.save(secondProduct);
        assertTrue(saveResult1);
        assertTrue(productDatabase.getProductList().contains(secondProduct));
    }

    @Test
    public void saveProductTestNotValidInput() {
        ProductDatabaseImpl productDatabase = new ProductDatabaseImpl();

        Product firstProduct = new Product("Laptop","Dell",400);
        Product secondProduct = new Product("Laptop","Dell",400);
        Product thirdProduct = new Product("LAPTOP","Dell",400);
        boolean saveResult1 = productDatabase.save(firstProduct);
        boolean saveResult2 = productDatabase.save(secondProduct);
        assertTrue(saveResult1);
        assertFalse(saveResult2);
        assertFalse(productDatabase.save(thirdProduct));
    }

    @Test
    public void generateProductIdTest() {
        ProductDatabaseImpl productDatabase = new ProductDatabaseImpl();

        Product firstProduct = new Product("Laptop","Dell",400);
        Product sameProduct = new Product("Laptop","Dell",400);
        Product secondProduct = new Product("Refrigerator","Electrolux",300);
        productDatabase.save(firstProduct);
        productDatabase.save(sameProduct);
        productDatabase.save(secondProduct);
        long firstId = firstProduct.getId();
        long secondId = secondProduct.getId();
        assertTrue(firstId == 1L);
        assertTrue(secondId == 2L);
    }

    @Test
    public void deleteByIdTest() {
        ProductDatabaseImpl productDatabase = new ProductDatabaseImpl();

        Product firstProduct = new Product("Laptop","Dell",400);
        Product secondProduct = new Product("Refrigerator","Electrolux",300);
        productDatabase.save(firstProduct);
        productDatabase.save(secondProduct);
        boolean deleteResult = productDatabase.deleteById(1L);
        assertTrue(deleteResult);
        assertFalse(productDatabase.getProductList().contains(firstProduct));
        assertTrue(productDatabase.getProductList().contains(secondProduct));
    }

    @Test
    public void deleteByIdTest2() {
        ProductDatabaseImpl productDatabase = new ProductDatabaseImpl();

        Product firstProduct = new Product("Laptop","Dell",400);
        Product secondProduct = new Product("Refrigerator","Electrolux",300);
        productDatabase.save(firstProduct);
        productDatabase.save(secondProduct);
        boolean deleteResult = productDatabase.deleteById(2L);
        assertTrue(deleteResult);
    }

    @Test
    public void deleteByIdTestNoIdInDatabase() {
        ProductDatabaseImpl productDatabase = new ProductDatabaseImpl();

        Product firstProduct = new Product("Laptop","Dell",400);
        Product secondProduct = new Product("Refrigerator","Electrolux",300);
        productDatabase.save(firstProduct);
        productDatabase.save(secondProduct);
        assertFalse(productDatabase.deleteById(7L));
    }

    @Test
    public void getProductListTest() {
        ProductDatabaseImpl productDatabase = new ProductDatabaseImpl();

        Product firstProduct = new Product("Laptop","Dell",400);
        Product secondProduct = new Product("Refrigerator","Electrolux",300);
        productDatabase.save(firstProduct);
        productDatabase.save(secondProduct);

        List<Product> listOfAllProducts = productDatabase.getProductList();

        assertTrue(listOfAllProducts.contains(firstProduct));
        assertTrue(listOfAllProducts.contains(secondProduct));
        assertEquals(listOfAllProducts.size(),2);
    }

    @Test
    public void changeProductTitleTest() {
        ProductDatabaseImpl productDatabase = new ProductDatabaseImpl();

        Product firstProduct = new Product("Laptop","Dell",400);
        Product secondProduct = new Product("Refrigerator","Electrolux",300);
        Product thirdProduct = new Product("TV","Radiotehnika",3);
        productDatabase.save(firstProduct);
        productDatabase.save(secondProduct);
        productDatabase.save(thirdProduct);

        productDatabase.changeTitle(1L,"Personal Computer");
        productDatabase.changeTitle(2L,"Holodiljnik");
        productDatabase.changeTitle(3L,"Televizor");

        String titleOfFirstProduct = firstProduct.getTitle();
        String titleOfSecondProduct = secondProduct.getTitle();
        String titleOfThirdProduct = thirdProduct.getTitle();

        assertTrue(titleOfFirstProduct.equals("Personal Computer"));
        assertTrue(titleOfSecondProduct.equals("Holodiljnik"));
        assertFalse(titleOfThirdProduct.equals("TV"));

        assertFalse(productDatabase.changeTitle(7L, "Nothing"));
    }

    @Test
    public void changeDescriptionTest() {
        ProductDatabaseImpl productDatabase = new ProductDatabaseImpl();

        Product firstProduct = new Product("Laptop","Dell",400);
        Product secondProduct = new Product("Refrigerator","Electrolux",300);
        Product thirdProduct = new Product("TV","Radiotehnika",3);
        productDatabase.save(firstProduct);
        productDatabase.save(secondProduct);
        productDatabase.save(thirdProduct);

        productDatabase.changeDescription(1L,"Samsung");
        productDatabase.changeDescription(2L,"LG");
        productDatabase.changeDescription(3L,"LCD Screens");

        String descriptionOfFirstProduct = firstProduct.getDescription();
        String descriptionOfSecondProduct = secondProduct.getDescription();
        String descriptionOfThirdProduct = thirdProduct.getDescription();

        assertTrue(descriptionOfFirstProduct.equals("Samsung"));
        assertFalse(descriptionOfSecondProduct.equals("Holodiljnik"));
        assertTrue(descriptionOfThirdProduct.equals("LCD Screens"));
        assertFalse(productDatabase.changeDescription(8L, "Nothing"));
    }

    @Test
    public void findAnyProductByTitleTest() {
        ProductDatabaseImpl productDatabase = new ProductDatabaseImpl();

        Product firstProduct = new Product("Laptop","Dell",400);
        Product secondProduct = new Product("Refrigerator","Electrolux",300);
        Product thirdProduct = new Product("TV","Radiotehnika",3);
        productDatabase.save(firstProduct);
        productDatabase.save(secondProduct);
        productDatabase.save(thirdProduct);

        Optional<Product> resultOfFindingLaptop = productDatabase.findAnyByTitle("Laptop");
        Optional<Product> resultOfFindingRefrigerator = productDatabase.findAnyByTitle("REFRIGERATOR");
        Optional<Product> resultOfFindingTV = productDatabase.findAnyByTitle("TV");
        Optional<Product> resultOfFindingMicrophone = productDatabase.findAnyByTitle("Microphone");

        assertTrue(resultOfFindingLaptop.isPresent());
        assertTrue(resultOfFindingRefrigerator.isPresent());
        assertTrue(resultOfFindingTV.isPresent());
        assertFalse(resultOfFindingMicrophone.isPresent());

    }

    @Test
    public void findAllProductsByTitleTest() {
        ProductDatabaseImpl productDatabase = new ProductDatabaseImpl();

        Product firstProduct = new Product("Laptop","Dell",400);
        Product secondProduct = new Product("Laptop","Acer",300);
        Product thirdProduct = new Product("TV","Radiotehnika",3);
        productDatabase.save(firstProduct);
        productDatabase.save(secondProduct);
        productDatabase.save(thirdProduct);

        List<Product> resultOfFindingAllLaptops = productDatabase.findAllByTitle("Laptop");
        List<Product> resultOfFindingAllTV = productDatabase.findAllByTitle("tv");
        List <Product> resultOfFindingAllPC = productDatabase.findAllByTitle("PC");

        assertTrue(resultOfFindingAllLaptops.contains(firstProduct));
        assertTrue(resultOfFindingAllLaptops.contains(secondProduct));
        assertEquals(resultOfFindingAllLaptops.size(),2);

        assertTrue(resultOfFindingAllTV.contains(thirdProduct));
        assertFalse(resultOfFindingAllTV.contains(secondProduct));
        assertEquals(resultOfFindingAllTV.size(),1);

        assertEquals(0, resultOfFindingAllPC.size());

    }
}
