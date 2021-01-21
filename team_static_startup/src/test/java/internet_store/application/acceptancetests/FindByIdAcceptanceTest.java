package internet_store.application.acceptancetests;

import internet_store.application.config.ProductListConfiguration;
import internet_store.application.core.database.ProductDatabase;
import internet_store.application.core.domain.Product;
import internet_store.application.core.requests.FindByIdRequest;
import internet_store.application.core.responses.FindByIdResponse;
import internet_store.application.core.services.FindByIdService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.*;

public class FindByIdAcceptanceTest {

    private ApplicationContext appContext;
    private ProductDatabase database;

    @Before
    public void setUp() {
        appContext =
                new AnnotationConfigApplicationContext(ProductListConfiguration.class);
        database = getDatabase();
        database.add(new Product("iPhone", "phone", new BigDecimal("900")));
        database.add(new Product("iMac", "pc", new BigDecimal("4000")));
    }

//    @Test
//    public void shouldFindById() {
//        FindByIdRequest request = new FindByIdRequest("2");
//        FindByIdResponse response = getFindByIdService().execute(request);
//
//        assertFalse(response.hasErrors());
//        assertFalse(response.getProductFoundById().isEmpty());
//        assertEquals(Optional.of(
//                newProduct(2L, "iMac", "pc", new BigDecimal("4000"))),
//                response.getProductFoundById());
//    }

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

    private ProductDatabase getDatabase() {
        return appContext.getBean(ProductDatabase.class);
    }

    private FindByIdService getFindByIdService() {
        return appContext.getBean(FindByIdService.class);
    }
}