package internet_store.application.core.services.customer;

import internet_store.application.core.database.jpa.JpaCustomerRepository;
import internet_store.application.core.domain.Customer;
import internet_store.application.core.requests.customer.AddCustomerRequest;
import internet_store.application.core.responses.customer.AddCustomerResponse;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.services.customer.validators.AddCustomerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AddCustomerService {

    @Autowired private JpaCustomerRepository customerRepository;
    @Autowired private AddCustomerValidator validator;

    public AddCustomerResponse execute(AddCustomerRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddCustomerResponse(errors);
        }
        Customer customer = new Customer();
        customer.setCustomerFirstName(request.getCustomerFirstName());
        customer.setCustomerSecondName(request.getCustomerSecondName());
        customer.setCustomerPhone(request.getCustomerPhone());
        customer.setCustomerEmail(request.getCustomerEMail());
        customer.setCustomerAddress(request.getCustomerAddress());

        customerRepository.save(customer);
        return new AddCustomerResponse(customer);
    }
}
