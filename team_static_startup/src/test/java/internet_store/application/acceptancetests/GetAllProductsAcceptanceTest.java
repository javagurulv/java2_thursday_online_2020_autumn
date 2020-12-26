package internet_store.application.acceptancetests;

import internet_store.application.config.ProductListConfiguration;
import internet_store.application.core.requests.AddProductRequest;
import internet_store.application.core.requests.GetAllProductsRequest;
import internet_store.application.core.responses.GetAllProductsResponse;
import internet_store.application.core.services.AddProductService;
import internet_store.application.core.services.GetAllProductsService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class GetAllProductsAcceptanceTest {

    private ApplicationContext appContext;

    @Before
    public void before() {
        appContext =
                new AnnotationConfigApplicationContext(ProductListConfiguration.class);
    }

//    @Test
//    public void shouldReturnCorrectProductList() {
//        AddProductRequest addProductRequest1 = new AddProductRequest(
//                "TV", "SONY", new BigDecimal("1000"));
//        getAddProductService().execute(addProductRequest1);
//
//        AddProductRequest addBookRequest2 = new AddProductRequest(
//                "Receiver", "Denon", new BigDecimal("1500"));
//        getAddProductService().execute(addBookRequest2);
//
//        GetAllProductsResponse response = getAllProductsService().execute(new GetAllProductsRequest());
//        assertEquals(response.getProductList().size(), 2);
//    }

    private AddProductService getAddProductService() {
        return appContext.getBean(AddProductService.class);
    }

    private GetAllProductsService getAllProductsService() {
        return appContext.getBean(GetAllProductsService.class);
    }

}