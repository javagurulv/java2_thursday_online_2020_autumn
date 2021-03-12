package internet_store.application.core.services.customer;

import internet_store.application.core.database.customer.CustomerRepository;
import internet_store.application.core.database.jpa.JpaCustomerRepository;
import internet_store.application.core.requests.customer.FindByCustomerFirstNameRequest;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.customer.FindByCustomerFirstNameResponse;
import internet_store.application.core.services.customer.validators.FindByCustomerFirstNameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindByCustomerFirstNameService {

    @Autowired
    FindByCustomerFirstNameValidator validator;
    @Autowired
    JpaCustomerRepository customerRepository;

    public FindByCustomerFirstNameResponse execute(FindByCustomerFirstNameRequest request) {
        List<CoreError> errors = validator.validate(request);
        String customerFirstName = request.getCustomerFirstName();

        if (!errors.isEmpty()) {
            return new FindByCustomerFirstNameResponse(errors, null);
        } else {
            return new FindByCustomerFirstNameResponse(null, customerRepository.findByFirstName(customerFirstName));
        }
    }

}
