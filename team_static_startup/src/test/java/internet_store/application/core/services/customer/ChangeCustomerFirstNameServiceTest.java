package internet_store.application.core.services.customer;

import internet_store.application.core.database.customer.CustomerRepository;
import internet_store.application.core.database.jpa.JpaCustomerRepository;
import internet_store.application.core.requests.customer.ChangeCustomerFirstNameRequest;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.customer.ChangeCustomerFirstNameResponse;
import internet_store.application.core.services.customer.validators.ChangeCustomerFirstNameValidator;
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
public class ChangeCustomerFirstNameServiceTest {

    @Mock private JpaCustomerRepository repository;
    @Mock private ChangeCustomerFirstNameValidator validator;
    @InjectMocks private ChangeCustomerFirstNameService service;

    @Test
    public void shouldReturnResponseWithoutErrors() {
        ChangeCustomerFirstNameRequest request = new ChangeCustomerFirstNameRequest(1L, "newName");
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        Mockito.when(repository.changeFirstName(1L, "newName")).thenReturn(1L);

        ChangeCustomerFirstNameResponse response = service.execute(request);

        assertTrue(response.isNameChanged());
    }

    @Test
    public void shouldReturnResponseWithError_whenValidationFails(){
        ChangeCustomerFirstNameRequest request = new ChangeCustomerFirstNameRequest(1L, "newName");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Customer Name", "Should not be empty"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        ChangeCustomerFirstNameResponse response = service.execute(request);
        assertFalse(response.isNameChanged());
        assertEquals(1, response.getErrors().size());
        assertEquals("Customer Name", response.getErrors().get(0).getField());
        assertEquals("Should not be empty", response.getErrors().get(0).getMessage());

    }


}