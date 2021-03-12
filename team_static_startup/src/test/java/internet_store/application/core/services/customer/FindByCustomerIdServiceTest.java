package internet_store.application.core.services.customer;

import internet_store.application.core.database.customer.CustomerRepository;
import internet_store.application.core.database.jpa.JpaCustomerRepository;
import internet_store.application.core.domain.Customer;
import internet_store.application.core.requests.customer.FindByCustomerIdRequest;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.customer.FindByCustomerIdResponse;
import internet_store.application.core.services.customer.validators.FindByCustomerIdValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class FindByCustomerIdServiceTest {

    @Mock
    JpaCustomerRepository customerRepository;
    @Mock FindByCustomerIdValidator validator;
    @InjectMocks FindByCustomerIdService service;

    @Test
    public void shouldReturnResponseWithoutErrors() {
        FindByCustomerIdRequest request = new FindByCustomerIdRequest("1");
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        Mockito.when(customerRepository.findById(1L)).thenReturn(Optional.of(
                new Customer("customerFirst", "customerLast")));

        FindByCustomerIdResponse response = service.execute(request);
        assertEquals(Optional.of(
                new Customer("customerFirst", "customerLast")),
                response.getCustomerFindById());
    }

    @Test
    public void shouldReturnResponseWithError_whenValidationFailsFromString() {
        FindByCustomerIdRequest request = new FindByCustomerIdRequest("id");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Customer Id", "Should be valid."));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        FindByCustomerIdResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(1, response.getErrors().size());
        assertEquals("Customer Id", response.getErrors().get(0).getField());
        assertEquals("Should be valid.", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnResponseWithError_whenValidationFailsFromNull() {
        FindByCustomerIdRequest request = new FindByCustomerIdRequest(null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Customer Id", "Should not be empty"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        FindByCustomerIdResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(1, response.getErrors().size());
        assertEquals("Customer Id", response.getErrors().get(0).getField());
        assertEquals("Should not be empty", response.getErrors().get(0).getMessage());
    }

}