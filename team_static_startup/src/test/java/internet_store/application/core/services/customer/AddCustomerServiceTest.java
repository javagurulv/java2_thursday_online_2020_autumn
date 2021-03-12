package internet_store.application.core.services.customer;

import internet_store.application.core.database.customer.CustomerRepository;
import internet_store.application.core.database.jpa.JpaCustomerRepository;
import internet_store.application.core.requests.customer.AddCustomerRequest;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.customer.AddCustomerResponse;
import internet_store.application.core.services.customer.validators.AddCustomerValidator;
import internet_store.application.core.services.matchers.CustomerMatcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;

@RunWith(MockitoJUnitRunner.class)
public class AddCustomerServiceTest {

    @Mock private JpaCustomerRepository customerRepository;
    @Mock private AddCustomerValidator validator;
    @InjectMocks private AddCustomerService service;

    @Test
    public void shouldReturnNoErrorsWhenValidatingWithFields() {
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        AddCustomerRequest request = new AddCustomerRequest("Ivan", "Ivanov");

        AddCustomerResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        Mockito.verify(customerRepository).save(argThat(new CustomerMatcher("Ivan",
                "Ivanov")));
    }

    @Test
    public void shouldReturnErrorWhenValidatingWithWrongFields() {
        AddCustomerRequest request = new AddCustomerRequest(" ", "Ivanov");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("FirstName", "must not be empty"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        AddCustomerResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "FirstName");
        assertEquals(response.getErrors().get(0).getMessage(), "must not be empty");

        Mockito.verifyNoInteractions(customerRepository);
    }

}