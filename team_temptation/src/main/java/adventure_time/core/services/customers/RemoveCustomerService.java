package adventure_time.core.services.customers;

import adventure_time.core.requests.customers.RemoveCustomerRequest;
import adventure_time.core.responses.CoreError;
import adventure_time.core.responses.customer.RemoveCustomerResponse;
import adventure_time.core.database.customers.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RemoveCustomerService {

    @Autowired
    private CustomerRepository database;
    @Autowired private RemoveCustomerRequestValidator validator;

    public RemoveCustomerResponse remove (RemoveCustomerRequest request) {

        List<CoreError> errors = validator.validate(request);

        if (!errors.isEmpty()) {
            return new RemoveCustomerResponse(errors);
        }

        if (!database.delete(request.getCustomerId())) {
            errors.add(new CoreError("customerId",
                    "There is no customer with ID: " + request.getCustomerId() + " in the database"));
            return new RemoveCustomerResponse(errors);
        } else {
            return new RemoveCustomerResponse();
        }
    }
}
