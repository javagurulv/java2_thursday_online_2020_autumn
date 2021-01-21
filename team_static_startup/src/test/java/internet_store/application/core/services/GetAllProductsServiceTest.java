package internet_store.application.core.services;

import internet_store.application.core.domain.Product;
import internet_store.application.core.database.ProductDatabase;
import internet_store.application.core.requests.GetAllProductsRequest;
import internet_store.application.core.responses.GetAllProductsResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(MockitoJUnitRunner.class)
public class GetAllProductsServiceTest {

    @Mock private ProductDatabase database;
    @InjectMocks private GetAllProductsService service;

    @Test
    public void shouldGetProductsFromDb() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("TV", "SONY", new BigDecimal("1000")));
        Mockito.when(database.getProductList()).thenReturn(products);
        GetAllProductsRequest request = new GetAllProductsRequest();
        GetAllProductsResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getProductList().size(), 1);
        assertEquals(response.getProductList().get(0).getName(), "TV");
        assertEquals(response.getProductList().get(0).getDescription(), "SONY");
    }

}