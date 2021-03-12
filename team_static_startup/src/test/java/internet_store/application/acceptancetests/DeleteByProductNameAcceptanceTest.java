package internet_store.application.acceptancetests;

import internet_store.application.config.SpringCoreConfiguration;
import internet_store.application.core.DatabaseCleaner;
import internet_store.application.core.requests.product.AddProductRequest;
import internet_store.application.core.requests.product.DeleteByProductNameRequest;
import internet_store.application.core.responses.product.DeleteByProductNameResponse;
import internet_store.application.core.services.product.AddProductService;
import internet_store.application.core.services.product.DeleteByProductNameService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import static org.junit.Assert.*;
import java.math.BigDecimal;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DeleteByProductNameAcceptanceTest {

    private ApplicationContext appContext;

    @Before
    public void before() {
        appContext = new AnnotationConfigApplicationContext(SpringCoreConfiguration.class);
        getDatabaseCleaner().clean();
    }
/*

    @Test
    public void shouldDeleteProductCorrectly() {
        AddProductRequest request = new AddProductRequest(
                "TV", "SONY", new BigDecimal("1000"));
        getAddProductService().execute(request);
        DeleteByProductNameRequest requestToDelete = new DeleteByProductNameRequest("TV");
        DeleteByProductNameResponse response = getDeleteByProductNameService().execute(requestToDelete);
        assertTrue(response.isProductRemoved());
        assertFalse(response.hasErrors());
    }

    @Test
    public void shouldNotDeleteProductIfNameNotCorrect() {
        AddProductRequest request = new AddProductRequest(
                "TV", "SONY", new BigDecimal("1000"));
        getAddProductService().execute(request);
        DeleteByProductNameRequest requestToDelete = new DeleteByProductNameRequest("qwe");
        DeleteByProductNameResponse response = getDeleteByProductNameService().execute(requestToDelete);
        assertFalse(response.isProductRemoved());
    }

    @Test
    public void shouldNotDeleteProductIfNameNotEntered() {
        AddProductRequest request = new AddProductRequest(
                "TV", "SONY", new BigDecimal("1000"));
        getAddProductService().execute(request);
        DeleteByProductNameRequest requestToDelete = new DeleteByProductNameRequest("");
        DeleteByProductNameResponse response = getDeleteByProductNameService().execute(requestToDelete);
        assertFalse(response.isProductRemoved());
        assertTrue(response.hasErrors());
        assertEquals("Product name", response.getErrors().get(0).getField());
        assertEquals("must not be empty", response.getErrors().get(0).getMessage());
    }
*/
    private AddProductService getAddProductService() {
        return appContext.getBean(AddProductService.class);
    }

    private DeleteByProductNameService getDeleteByProductNameService() {
        return appContext.getBean(DeleteByProductNameService.class);
    }

    private DatabaseCleaner getDatabaseCleaner() {
        return appContext.getBean(DatabaseCleaner.class);
    }

}
