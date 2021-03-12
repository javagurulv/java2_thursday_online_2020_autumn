package internet_store.application.acceptancetests;

import internet_store.application.config.SpringCoreConfiguration;
import internet_store.application.core.DatabaseCleaner;
import internet_store.application.core.requests.product.AddProductRequest;
import internet_store.application.core.requests.product.DeleteByProductIdRequest;
import internet_store.application.core.responses.product.DeleteByProductIdResponse;
import internet_store.application.core.services.product.AddProductService;
import internet_store.application.core.services.product.DeleteByProductIdService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Profile;
import java.math.BigDecimal;
import static org.junit.Assert.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DeleteByIdAcceptanceTest {

    private ApplicationContext appContext;

    @Before
    public void setUp() {
        appContext = new AnnotationConfigApplicationContext(SpringCoreConfiguration.class);
        getDatabaseCleaner().clean();
    }
/*
    @Test
    public void shouldDeleteWhenIdIsInDatabase() {
        AddProductRequest addRequest = new AddProductRequest("A1",
                "B1", new BigDecimal("1"));
        getAddProductService().execute(addRequest);
        DeleteByProductIdRequest deleteRequest = new DeleteByProductIdRequest(1L);
        DeleteByProductIdResponse deleteResponse = getDeleteByProductIdService().execute(deleteRequest);
        assertTrue(deleteResponse.isProductRemoved());
        assertFalse(deleteResponse.hasErrors());
    }

    @Test
    public void shouldNotDeleteWhenIdIsNotInDatabase() {
        DeleteByProductIdRequest deleteRequest = new DeleteByProductIdRequest(2L);
        DeleteByProductIdResponse deleteResponse = getDeleteByProductIdService().execute(deleteRequest);
        assertFalse(deleteResponse.isProductRemoved());
    }

    @Test
    public void shouldNotDeleteWhenIdIsNotProvided() {
        DeleteByProductIdRequest deleteRequest = new DeleteByProductIdRequest(null);
        DeleteByProductIdResponse deleteResponse = getDeleteByProductIdService().execute(deleteRequest);
        assertFalse(deleteResponse.isProductRemoved());
        assertTrue(deleteResponse.hasErrors());
        assertEquals(1, deleteResponse.getErrors().size());
        assertEquals("Product ID", deleteResponse.getErrors().get(0).getField());
        assertEquals("Should not be empty.", deleteResponse.getErrors().get(0).getMessage());
    }
*/
    private AddProductService getAddProductService() {
        return appContext.getBean(AddProductService.class);
    }

    private DeleteByProductIdService getDeleteByProductIdService() {
        return appContext.getBean(DeleteByProductIdService.class);
    }

    private DatabaseCleaner getDatabaseCleaner() {
        return appContext.getBean(DatabaseCleaner.class);
    }

}
