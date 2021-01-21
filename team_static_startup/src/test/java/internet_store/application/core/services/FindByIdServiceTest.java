package internet_store.application.core.services;

import internet_store.application.core.database.ProductDatabase;
import internet_store.application.core.domain.Product;
import internet_store.application.core.requests.FindByIdRequest;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.FindByIdResponse;
import internet_store.application.core.services.validators.FindByIdValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class FindByIdServiceTest {

    @Mock
    ProductDatabase database;
    @Mock FindByIdValidator validator;
    @InjectMocks FindByIdService service;

    @Test
    public void shouldReturnResponseWithoutErrors() {
        FindByIdRequest request = new FindByIdRequest("1");
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        Mockito.when(database.findById(1L)).thenReturn(Optional.of(
                new Product("product", "desc", new BigDecimal("1"))));

        FindByIdResponse response = service.execute(request);
        assertEquals(Optional.of(
                new Product("product", "desc", new BigDecimal("1"))),
                response.getProductFoundById());
    }

    @Test
    public void shouldReturnResponseWithError_whenValidationFailsFromString() {
        FindByIdRequest request = new FindByIdRequest("id");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Product ID", "Should be valid."));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        FindByIdResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(1, response.getErrors().size());
        assertEquals("Product ID", response.getErrors().get(0).getField());
        assertEquals("Should be valid.", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnResponseWithError_whenValidationFailsFromNull() {
        FindByIdRequest request = new FindByIdRequest(null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Product ID", "Should not be empty."));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        FindByIdResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(1, response.getErrors().size());
        assertEquals("Product ID", response.getErrors().get(0).getField());
        assertEquals("Should not be empty.", response.getErrors().get(0).getMessage());
    }

}