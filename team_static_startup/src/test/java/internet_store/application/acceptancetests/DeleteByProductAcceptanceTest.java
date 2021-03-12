package internet_store.application.acceptancetests;

import internet_store.application.config.SpringCoreConfiguration;
import internet_store.application.core.DatabaseCleaner;
import internet_store.application.core.requests.product.AddProductRequest;
import internet_store.application.core.requests.product.DeleteByProductRequest;
import internet_store.application.core.responses.product.DeleteByProductResponse;
import internet_store.application.core.services.product.AddProductService;
import internet_store.application.core.services.product.DeleteProductByProductService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import static org.junit.Assert.*;
import java.math.BigDecimal;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DeleteByProductAcceptanceTest {

    private ApplicationContext appContext;

    @Before
    public void setUp() {
        appContext = new AnnotationConfigApplicationContext(SpringCoreConfiguration.class);
        getDatabaseCleaner().clean();
    }

    @Test
    public void shouldDeleteProductWithNoErrors() {
        AddProductRequest request = new AddProductRequest("tv",
                "good tv", new BigDecimal("399.99"));
        getAddProductService().execute(request);

        DeleteByProductRequest requestToDelete = new DeleteByProductRequest("tv",
                "good tv", new BigDecimal("399.99"));
        DeleteByProductResponse response = getDeleteProductByProductService().execute(requestToDelete);

        assertEquals(request.getProductName(), response.getDeletedProduct().getName());
        assertEquals(request.getProductDescription(), response.getDeletedProduct().getDescription());
        assertEquals(request.getProductPrice(), response.getDeletedProduct().getPrice());
    }

    @Test
    public void shouldNotDeleteProductErrorInDescription() {
        AddProductRequest request = new AddProductRequest("tv",
                "good tv", new BigDecimal("399.99"));
        getAddProductService().execute(request);

        DeleteByProductRequest requestToDelete = new DeleteByProductRequest("tv",
                " ", new BigDecimal("399.99"));
        DeleteByProductResponse response = getDeleteProductByProductService().execute(requestToDelete);

        assertNull(response.getDeletedProduct());
        assertTrue(response.hasErrors());
        assertEquals("Description", response.getErrors().get(0).getField());
        assertEquals("must not be empty", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldNotDeleteProductErrorInName() {
        AddProductRequest request = new AddProductRequest("tv",
                "good tv", new BigDecimal("399.99"));
        getAddProductService().execute(request);

        DeleteByProductRequest requestToDelete = new DeleteByProductRequest(null,
                "good tv", new BigDecimal("399.99"));
        DeleteByProductResponse response = getDeleteProductByProductService().execute(requestToDelete);

        assertNull(response.getDeletedProduct());
        assertTrue(response.hasErrors());
        assertEquals("Name", response.getErrors().get(0).getField());
        assertEquals("must not be empty", response.getErrors().get(0).getMessage());
    }

    private DeleteProductByProductService getDeleteProductByProductService() {
        return appContext.getBean(DeleteProductByProductService.class);
    }

    private AddProductService getAddProductService() {
        return appContext.getBean(AddProductService.class);
    }

    private DatabaseCleaner getDatabaseCleaner() {
        return appContext.getBean(DatabaseCleaner.class);
    }

}
