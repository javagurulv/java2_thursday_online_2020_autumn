package internet_store.application.core.services.product;

import internet_store.application.core.database.jpa.JpaProductRepository;
import internet_store.application.core.requests.product.ChangeProductNameRequest;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.product.ChangeProductNameResponse;
import internet_store.application.core.services.product.validators.ChangeProductNameValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ChangeProductNameServiceTest {

    @Mock private JpaProductRepository productRepository;
    @Mock private ChangeProductNameValidator validator;
    @InjectMocks private ChangeProductNameService service;


    @Test
    public void shouldReturnResponseWithoutErrors() {
        ChangeProductNameRequest request = new ChangeProductNameRequest(1L, "newName");
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        Mockito.when(productRepository.changeProductName(1L, "newName")).thenReturn(1);

        ChangeProductNameResponse response = service.execute(request);

        assertTrue(response.isNameChanged());
    }


    @Test
    public void shouldReturnResponseWithError_whenValidationFails() {
        ChangeProductNameRequest request = new ChangeProductNameRequest(1L, "");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Product new name", "Should not be empty."));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        ChangeProductNameResponse response = service.execute(request);

        assertFalse(response.isNameChanged());
        assertEquals(1, response.getErrors().size());
        assertEquals("Product new name", response.getErrors().get(0).getField());
        assertEquals("Should not be empty.", response.getErrors().get(0).getMessage());
    }

}