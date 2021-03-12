package internet_store.application.acceptancetests;

import internet_store.application.config.SpringCoreConfiguration;
import internet_store.application.core.DatabaseCleaner;
import internet_store.application.core.database.jpa.JpaProductRepository;
import internet_store.application.core.database.product.ProductRepository;
import internet_store.application.core.domain.Product;
import internet_store.application.core.requests.product.ChangeProductNameRequest;
import internet_store.application.core.responses.product.ChangeProductNameResponse;
import internet_store.application.core.responses.product.FindByProductIdResponse;
import internet_store.application.core.services.product.ChangeProductNameService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Profile;
import static org.junit.Assert.*;

import java.math.BigDecimal;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ChangeProductNameAcceptanceTest {

    private ApplicationContext appContext;
    private JpaProductRepository productRepository;
    private Product product;

    @Before
    public void setUp() {
        appContext = new AnnotationConfigApplicationContext(SpringCoreConfiguration.class);
        getDatabaseCleaner().clean();
        productRepository = getRepository();
        productRepository.save(new Product("iPhone", "phone", new BigDecimal("900")));
        productRepository.save(new Product("iMac", "pc", new BigDecimal("4000")));
    }
/*

    @Test
    public void shouldChangeProductName() {
        ChangeProductNameRequest request = new ChangeProductNameRequest(1L, "iPhone12");
        ChangeProductNameResponse response = getChangeProductNameService().execute(request);

        assertTrue(response.isNameChanged());
        assertEquals("iPhone12", productRepository.findAll().get(0).getName());
        assertNull(response.getErrors());
    }

    @Test
    public void shouldNotChangeNameWhenProductNotFound() {
        ChangeProductNameRequest request = new ChangeProductNameRequest(3L, "iPhone12");
        ChangeProductNameResponse response = getChangeProductNameService().execute(request);

        assertFalse(response.isNameChanged());
        assertNull(response.getErrors());
    }

    @Test
    public void shouldReturnErrorWhenNewNameIsEmpty() {
        ChangeProductNameRequest request = new ChangeProductNameRequest(1L, "");
        ChangeProductNameResponse response = getChangeProductNameService().execute(request);

        assertFalse(response.isNameChanged());
        assertEquals(1, response.getErrors().size());
        assertEquals("Product new name", response.getErrors().get(0).getField());
        assertEquals("Should not be empty.", response.getErrors().get(0).getMessage());
    }
*/
    private JpaProductRepository getRepository() {
        return appContext.getBean(JpaProductRepository.class);
    }

    private ChangeProductNameService getChangeProductNameService() {
        return appContext.getBean(ChangeProductNameService.class);
    }

    private DatabaseCleaner getDatabaseCleaner() {
        return appContext.getBean(DatabaseCleaner.class);
    }

}