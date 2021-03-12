package internet_store.application.core.services.customer;

import internet_store.application.core.database.customer.CustomerRepository;
import internet_store.application.core.database.jpa.JpaCustomerRepository;
import internet_store.application.core.requests.customer.FindByCustomerIdRequest;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.customer.FindByCustomerIdResponse;
import internet_store.application.core.services.customer.validators.FindByCustomerIdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindByCustomerIdService {

    @Autowired private JpaCustomerRepository customerRepository;
    @Autowired private FindByCustomerIdValidator validator;

    public FindByCustomerIdResponse execute(FindByCustomerIdRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new FindByCustomerIdResponse(errors);
        }
        Long id = Long.valueOf(request.getCustomerId());
        return new FindByCustomerIdResponse(customerRepository.findById(id));
    }

}
