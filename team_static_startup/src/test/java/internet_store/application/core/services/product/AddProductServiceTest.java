package internet_store.application.core.services.product;

import internet_store.application.core.database.jpa.JpaProductRepository;
import internet_store.application.core.requests.product.AddProductRequest;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.product.AddProductResponse;
import internet_store.application.core.services.matchers.ProductMatcher;
import internet_store.application.core.services.product.validators.AddProductValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;

@RunWith(MockitoJUnitRunner.class)
public class AddProductServiceTest {

    @Mock private JpaProductRepository productRepository;
    @Mock private AddProductValidator validator;
    @InjectMocks private AddProductService service;

    @Test
    public void shouldReturnNoErrorsWhenValidatingWithFields() {
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        AddProductRequest request = new AddProductRequest("tv", "nice tv",
                new BigDecimal("399.99"));
        AddProductResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        Mockito.verify(productRepository).save(argThat(new ProductMatcher("tv", "nice tv",
                new BigDecimal("399.99"))));
    }

    @Test
    public void shouldReturnErrorWhenValidatingWithWrongFields() {
        AddProductRequest request = new AddProductRequest(" ", "nice tv",
                new BigDecimal("399.99"));
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Name", "must not be empty"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        AddProductResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "Name");
        assertEquals(response.getErrors().get(0).getMessage(), "must not be empty");

        Mockito.verifyNoInteractions(productRepository);
    }


}