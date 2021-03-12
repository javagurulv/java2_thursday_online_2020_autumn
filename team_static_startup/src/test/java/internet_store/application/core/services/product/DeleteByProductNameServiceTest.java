package internet_store.application.core.services.product;

import internet_store.application.core.database.jpa.JpaProductRepository;
import internet_store.application.core.requests.product.DeleteByProductNameRequest;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.product.DeleteByProductNameResponse;
import internet_store.application.core.services.product.validators.DeleteByProductNameValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class DeleteByProductNameServiceTest {

    @Mock private JpaProductRepository productRepository;
    @Mock private DeleteByProductNameValidator validator;
    @InjectMocks private DeleteByProductNameService service;

    @Test
    public void shouldReturnResponseWithErrorsWhenValidatorFails() {
        DeleteByProductNameRequest request = new DeleteByProductNameRequest(null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Product Name", "Product Name must not be empty."));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        DeleteByProductNameResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "Product Name");
        Mockito.verify(validator).validate(request);
        Mockito.verify(validator).validate(any());
        Mockito.verifyNoInteractions(productRepository);
    }

    @Test
    public void shouldReturnErrorWhenProductNameNotProvided() {
        DeleteByProductNameRequest request = new DeleteByProductNameRequest(null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("ProductName", "must not be empty"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        DeleteByProductNameResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "ProductName");
        assertEquals(response.getErrors().get(0).getMessage(), "must not be empty");
    }

    @Test
    public void shouldDeleteProductWithProductNameFromDatabase() {
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        Mockito.when(productRepository.deleteByProductName("Receiver")).thenReturn(1);
        DeleteByProductNameRequest request = new DeleteByProductNameRequest("Receiver");
        DeleteByProductNameResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertTrue(response.isProductRemoved());
    }

}