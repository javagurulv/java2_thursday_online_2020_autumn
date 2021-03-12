package internet_store.application.acceptancetests;

import internet_store.application.config.SpringCoreConfiguration;
import internet_store.application.core.DatabaseCleaner;
import internet_store.application.core.requests.product.AddProductRequest;
import internet_store.application.core.responses.product.GetAllProductsResponse;
import internet_store.application.core.services.product.AddProductService;
import internet_store.application.core.services.product.GetAllProductsService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Profile;
import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GetAllProductsAcceptanceTest {

    private ApplicationContext appContext;

    @Before
    public void before() {
        appContext = new AnnotationConfigApplicationContext(SpringCoreConfiguration.class);
        getDatabaseCleaner().clean();
    }

    @Test
    public void shouldReturnCorrectProductList() {
        AddProductRequest addProductRequest1 = new AddProductRequest(
                "TV", "SONY", new BigDecimal("1000"));
        getAddProductService().execute(addProductRequest1);

        AddProductRequest addBookRequest2 = new AddProductRequest(
                "Receiver", "Denon", new BigDecimal("1500"));
        getAddProductService().execute(addBookRequest2);

        GetAllProductsResponse response = getAllProductsService().execute();
        assertEquals(response.getProductList().size(), 2);
    }

    private AddProductService getAddProductService() {
        return appContext.getBean(AddProductService.class);
    }

    private GetAllProductsService getAllProductsService() {
        return appContext.getBean(GetAllProductsService.class);
    }

    private DatabaseCleaner getDatabaseCleaner() {
        return appContext.getBean(DatabaseCleaner.class);
    }

}