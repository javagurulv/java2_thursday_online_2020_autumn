package internet_store.application.core.services.product;

import internet_store.application.core.database.jpa.JpaProductRepository;
import internet_store.application.core.requests.product.UpdateProductRequest;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.product.UpdateProductResponse;
import internet_store.application.core.services.product.validators.UpdateProductValidator;
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
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class UpdateProductServiceTest {

    @Mock private JpaProductRepository productRepository;
    @Mock private UpdateProductValidator validator;
    @InjectMocks private UpdateProductService service;

    @Test
    public void shouldReturnNoErrorsWhenValidatingWithFields() {
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        UpdateProductRequest request = new UpdateProductRequest(
                null, "newName", "newDescription", new BigDecimal("399.99"));
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("id", "Not fount!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        UpdateProductResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "id");
        assertEquals(response.getErrors().get(0).getMessage(), "Not fount!");

        Mockito.verifyNoInteractions(productRepository);
    }


}