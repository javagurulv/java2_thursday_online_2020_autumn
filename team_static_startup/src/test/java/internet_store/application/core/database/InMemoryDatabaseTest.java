/*package internet_store.application.core.database;

import internet_store.application.core.domain.Product;
import org.junit.Before;
import org.junit.Test;
import java.math.BigDecimal;
import java.util.List;
import static org.junit.Assert.*;

public class InMemoryDatabaseTest {
    InMemoryDatabase productDatabase;

    @Before
    public void setUp() {
        productDatabase = new InMemoryDatabase();
    }

    @Test
    public void shouldSaveNewProduct() {
        productDatabase.add(new Product("phone", "good phone", new BigDecimal("327.01")));
        productDatabase.add(new Product("tv", "good tv", new BigDecimal("499.99")));
        assertEquals(2, productDatabase.getProductList().size());
    }

    @Test
    public void shouldDeleteProductByProductId() {
        productDatabase.add(new Product("tv", "good tv", new BigDecimal("499.99")));
        productDatabase.deleteByProductId(1L);
        assertEquals(0, productDatabase.getProductList().size());
    }

    @Test
    public void shouldDeleteProductByProductObject() {
        Product tv = new Product("tv", "good tv", new BigDecimal("499.99"));
        productDatabase.add(tv);
        productDatabase.delete(tv);
        assertEquals(0, productDatabase.getProductList().size());
    }

    @Test
    public void shouldDeleteProductByProductName() {
        productDatabase.add(new Product("tv", "good tv", new BigDecimal("499.99")));
        productDatabase.deleteByProductName("tv");
        assertEquals(0, productDatabase.getProductList().size());
    }

    @Test
    public void shouldFindProductByProductName() {
        productDatabase.add(new Product("tv", "good tv", new BigDecimal("499.99")));
        productDatabase.add(new Product("tv", "good tv, good", new BigDecimal("399.99")));
        List<Product> testArray = productDatabase.findByProductName("tv");
        assertEquals(2, testArray.size());
    }

    @Test
    public void shouldChangeProductName() {
        productDatabase.add(new Product("iphone", "mobile phone", new BigDecimal("900.00")));
        productDatabase.add(new Product("macbook", "notebook", new BigDecimal("2000.00")));
        boolean result = productDatabase.changeProductName(2L, "macbook2");
        assertTrue(result);
        assertEquals("macbook2", productDatabase.getProductList().get(1).getName());
    }

    @Test
    public void shouldChangeProductName_whenNewNameIsCompound() {
        productDatabase.add(new Product("TV", "SONY", new BigDecimal("900.00")));
        productDatabase.add(new Product("TV", "SAMSUNG", new BigDecimal("1000.00")));
        boolean result = productDatabase.changeProductName(2L, "TV Set");
        assertTrue(result);
        assertEquals("TV Set", productDatabase.getProductList().get(1).getName());
    }

    @Test
    public void shouldNotChangeProductName_whenIdNotFound() {
        productDatabase.add(new Product("iphone", "mobile phone", new BigDecimal("900.00")));
        productDatabase.add(new Product("macbook", "notebook", new BigDecimal("2000.00")));
        boolean result = productDatabase.changeProductName(3L, "macbook2");
        assertFalse(result);
    }

}

 */