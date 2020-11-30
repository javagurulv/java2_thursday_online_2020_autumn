package internet_store.acceptance_test;

import internet_store.ApplicationContext;
import internet_store.core.domain.Product;
import internet_store.core.requests.product.AddProductRequest;
import internet_store.core.requests.product.GetProductsRequest;
import internet_store.core.response.product.GetProductsResponse;
import internet_store.core.services.product.AddProductService;
import internet_store.core.services.product.GetAllProductsService;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AcceptanceTest1 {

    ApplicationContext applicationContext = new ApplicationContext();

    @Test
    public void test(){
        Product product = new Product("Milk", "10%", 12);
        AddProductRequest addProductRequest = new AddProductRequest(product);
        addProductService().execute(addProductRequest);
        addProductService().execute(addProductRequest);

        GetProductsRequest getProductsRequest = new GetProductsRequest();
        GetProductsResponse getProductsResponse = getAllProductsService().execute(getProductsRequest);

        assertTrue(getProductsResponse.getProducts().size() == 1);

    }

    private AddProductService addProductService(){
        return applicationContext.getBean(AddProductService.class);
    }

    private GetAllProductsService getAllProductsService(){
        return applicationContext.getBean(GetAllProductsService.class);
    }
}
