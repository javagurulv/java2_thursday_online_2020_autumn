package internet_store.application.acceptancetests;

import internet_store.application.config.AppConfig;
import internet_store.application.core.requests.product.AddProductRequest;
import internet_store.application.core.requests.product.GetAllProductsRequest;
import internet_store.application.core.responses.product.GetAllProductsResponse;
import internet_store.application.core.services.product.AddProductService;
import internet_store.application.core.services.product.GetAllProductsService;
import internet_store.application.database_cleaner.DatabaseCleaner;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@Profile("hibernate")
public class AddProductAcceptanceTest {

/*
    private ApplicationContext appContext;

    @Before
    public void setUp(){
        appContext = new AnnotationConfigApplicationContext(AppConfig.class);
        getDatabaseCleaner().clean();
    }

    @Test
    public void shouldReturnNoErrorsWhenAddingBooks() {
        AddProductRequest addProductRequest1 = new AddProductRequest("tv1",
                "good tv1", new BigDecimal("399.99"));
        getAddProductService().execute(addProductRequest1);

        AddProductRequest addProductRequest2 = new AddProductRequest("tv2",
                "good tv2", new BigDecimal("399.99"));
        getAddProductService().execute(addProductRequest2);

        GetAllProductsRequest request = new GetAllProductsRequest();
        GetAllProductsResponse response = getAllProductsService().execute(request);
        assertEquals(2, response.getProductList().size());
    }

    @Test
    public void shouldNotAddBookWithWrongParameterName() {
        AddProductRequest addProductRequest = new AddProductRequest(" ",
                "good tv", new BigDecimal("399.99"));
        getAddProductService().execute(addProductRequest);

        GetAllProductsRequest request = new GetAllProductsRequest();
        GetAllProductsResponse response = getAllProductsService().execute(request);
        assertEquals(0, response.getProductList().size());
    }

    @Test
    public void shouldNotAddBookWithWrongParameterDescription() {
        AddProductRequest addProductRequest = new AddProductRequest("tv",
                null, new BigDecimal("399.99"));
        getAddProductService().execute(addProductRequest);

        GetAllProductsRequest request = new GetAllProductsRequest();
        GetAllProductsResponse response = getAllProductsService().execute(request);
        assertEquals(0, response.getProductList().size());
    }

    @Test
    public void shouldNotAddBookWithWrongParameterPrice() {
        AddProductRequest addProductRequest = new AddProductRequest("tv",
                "good tv", null);
        getAddProductService().execute(addProductRequest);

        GetAllProductsRequest request = new GetAllProductsRequest();
        GetAllProductsResponse response = getAllProductsService().execute(request);
        assertEquals(0, response.getProductList().size());
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
*/

}
