package internet_store.application.core.services;

import internet_store.application.core.database.ProductDatabase;
import internet_store.application.core.requests.DeleteByProductIdRequest;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.DeleteByProductIdResponse;
import internet_store.application.core.services.validators.DeleteByProductIdValidator;
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
public class DeleteByProductIdServiceTest {

    @Mock private ProductDatabase database;
    @Mock private DeleteByProductIdValidator validator;
    @InjectMocks DeleteByProductIdService service;

    @Test
    public void shouldReturnListWithOneError () {
        DeleteByProductIdRequest request = new DeleteByProductIdRequest(null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Product ID", "Should not be empty"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        DeleteByProductIdResponse response = service.execute(request);
        assertTrue (response.hasErrors());
        assertEquals(1, response.getErrors().size());
        assertEquals("Product ID", response.getErrors().get(0).getField());
        assertEquals("Should not be empty", response.getErrors().get(0).getMessage());
}

    @Test
    public void shouldNotInteractWithDatabaseWhenError () {
        DeleteByProductIdRequest request = new DeleteByProductIdRequest(null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Product ID", "Should not be empty"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        DeleteByProductIdResponse response = service.execute(request);
        Mockito.verifyNoInteractions(database);
    }

@Test
   public void shouldDeleteProductByIdFromDatabase() {
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        Mockito.when(database.deleteByProductId(1L)).thenReturn(true);
        DeleteByProductIdRequest request = new DeleteByProductIdRequest(1L);
        DeleteByProductIdResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertTrue(response.isProductRemoved());
    }
}
