package internet_store.application.core.services.product;

import internet_store.application.core.database.jpa.JpaProductRepository;
import internet_store.application.core.domain.Product;
import internet_store.application.core.requests.product.FindByIdRequest;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.product.FindByProductIdResponse;
import internet_store.application.core.services.product.validators.FindByProductIdValidator;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class FindByProductIdServiceTest {

    @Mock JpaProductRepository productRepository;
    @Mock FindByProductIdValidator validator;
    @InjectMocks FindByProductIdService service;

    @Test
    public void shouldReturnResponseWithoutErrors() {
        FindByIdRequest request = new FindByIdRequest("1");
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(
                new Product("product", "desc", new BigDecimal("1"))));

        FindByProductIdResponse response = service.execute(request);
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

        FindByProductIdResponse response = service.execute(request);
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

        FindByProductIdResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(1, response.getErrors().size());
        assertEquals("Product ID", response.getErrors().get(0).getField());
        assertEquals("Should not be empty.", response.getErrors().get(0).getMessage());
    }

}