package internet_store.application.core.services.product;

import internet_store.application.core.database.jpa.JpaProductRepository;
import internet_store.application.core.requests.product.DeleteProductRequest;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.product.DeleteProductResponse;
import internet_store.application.core.services.product.validators.DeleteProductValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class DeleteProductServiceTest {

    @Mock private JpaProductRepository productRepository;
    @Mock private DeleteProductValidator validator;
    @InjectMocks DeleteProductService service;


    @Test
    public void shouldReturnListWithOneError () {
        DeleteProductRequest request = new DeleteProductRequest(null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("id", "must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        DeleteProductResponse response = service.execute(request);
        assertTrue (response.hasErrors());
        assertEquals(1, response.getErrors().size());
        assertEquals("id", response.getErrors().get(0).getField());
        assertEquals("must not be empty!", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldNotInteractWithDatabaseWhenError () {
        DeleteProductRequest request = new DeleteProductRequest(null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("id", "must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        DeleteProductResponse response = service.execute(request);
        Mockito.verifyNoInteractions(productRepository);
    }

}