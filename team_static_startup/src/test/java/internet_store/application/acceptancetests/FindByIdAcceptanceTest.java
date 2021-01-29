package internet_store.application.acceptancetests;

import internet_store.lesson_6.config.ProductListConfiguration;
import internet_store.lesson_6.core.database.Database;
import internet_store.lesson_6.core.domain.Product;
import internet_store.lesson_6.core.requests.FindByIdRequest;
import internet_store.lesson_6.core.responses.FindByIdResponse;
import internet_store.lesson_6.core.services.FindByIdService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.util.Optional;
import static org.junit.Assert.*;
import org.springframework.context.annotation.Profile;

// @Profile("inmemory")
public class FindByIdAcceptanceTest {

    private ApplicationContext appContext;
    private Database database;

    @Before
    public void setUp() {
        appContext =
                new AnnotationConfigApplicationContext(ProductListConfiguration.class);
        database = getDatabase();
        database.add(new Product("iPhone", "phone", new BigDecimal("900")));
        database.add(new Product("iMac", "pc", new BigDecimal("4000")));
    }

    @Test
    public void shouldFindById() {
        FindByIdRequest request = new FindByIdRequest("2");
        FindByIdResponse response = getFindByIdService().execute(request);

        assertFalse(response.hasErrors());
        assertFalse(response.getProductFoundById().isEmpty());
        assertEquals(Optional.of(
                newProduct(2L, "iMac", "pc", new BigDecimal("4000"))),
                response.getProductFoundById());
    }

    @Test
    public void shouldNotFindWhenIdIsNotExist() {
        FindByIdRequest request = new FindByIdRequest("5");
        FindByIdResponse response = getFindByIdService().execute(request);

        assertFalse(response.hasErrors());
        assertEquals(Optional.empty(),
                response.getProductFoundById());
    }

    @Test
    public void shouldReturnErrorWhenIdIsEmpty() {
        FindByIdRequest request = new FindByIdRequest("");
        FindByIdResponse response = getFindByIdService().execute(request);

        assertTrue(response.hasErrors());
        assertEquals(1, response.getErrors().size());
        assertEquals("Product ID", response.getErrors().get(0).getField());
        assertEquals("Should not be empty.", response.getErrors().get(0).getMessage());
    }

    private Product newProduct(Long id, String name, String description, BigDecimal price) {
        Product product = new Product(name, description, price);
        product.setId(id);
        return product;
    }

    private Database getDatabase() {
        return appContext.getBean(Database.class);
    }

    private FindByIdService getFindByIdService() {
        return appContext.getBean(FindByIdService.class);
    }
}