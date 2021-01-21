package internet_store.application.core.services;

import internet_store.application.core.database.ProductDatabase;
import internet_store.application.core.domain.Product;
import internet_store.application.core.requests.DeleteByProductRequest;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.DeleteByProductResponse;
import internet_store.application.core.services.matchers.ProductMatcher;
import internet_store.application.core.services.validators.DeleteByProductValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;

@RunWith(MockitoJUnitRunner.class)
public class DeleteProductByProductServiceTest {

    @Mock private ProductDatabase database;
    @Mock private DeleteByProductValidator validator;
    @InjectMocks private DeleteProductByProductService service;

    @Test
    public void shouldDeleteProductWithoutErrors() {
        Product product = new Product("tv", "good tv", new BigDecimal("399.99"));
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        Mockito.when(database.delete(product)).thenReturn(true);

        DeleteByProductRequest request = new DeleteByProductRequest(product.getName(), product.getDescription(),
                product.getPrice());
        DeleteByProductResponse response = service.execute(request);

        assertFalse(response.hasErrors());
        Mockito.verify(database).delete(argThat(new ProductMatcher("tv",
                "good tv", new BigDecimal("399.99"))));
    }

    @Test
    public void shouldReturnErrorWhenProductIsNull() {
        DeleteByProductRequest request = new DeleteByProductRequest(null,
                null, null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Name", "must not be empty"));

        Mockito.when(validator.validate(request)).thenReturn(errors);

        DeleteByProductResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "Name");
        assertEquals(response.getErrors().get(0).getMessage(), "must not be empty");
    }

    @Test
    public void shouldReturnErrorWhenProductDescriptionIsEmpty() {
        Product product = new Product("tv"," ", new BigDecimal("399.99"));
        DeleteByProductRequest request = new DeleteByProductRequest(product.getName(),
                product.getDescription(), product.getPrice());

        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Description", "must not be empty"));

        Mockito.when(validator.validate(request)).thenReturn(errors);

        DeleteByProductResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "Description");
        assertEquals(response.getErrors().get(0).getMessage(), "must not be empty");
    }

}