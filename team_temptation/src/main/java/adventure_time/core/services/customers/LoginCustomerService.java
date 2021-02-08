package adventure_time.core.services.customers;

import adventure_time.core.domain.Customers;
import adventure_time.core.requests.customers.LoginCustomerRequest;
import adventure_time.core.responses.CoreError;
import adventure_time.core.responses.customer.LoginCustomerResponse;
import adventure_time.database.customers.DatabaseCustomers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class LoginCustomerService {

    @Autowired
    private DatabaseCustomers database;
    @Autowired
    private LoginCustomerRequestValidator validator;

    public LoginCustomerResponse loginCustomer (LoginCustomerRequest request) {

        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new LoginCustomerResponse(errors);
        }

        Optional<Customers> customer = database.findByEmail(request.getEmail());

        if (customer.isPresent()) {
            if (customer.get().
                    getCustomerPassword().
                    equals(request.getEmail())) {
                return new LoginCustomerResponse(customer.get());   // customer found, passwords matched
            } else {
                errors.add
                        (new CoreError(
                                "customerPassword",
                                "Passwords did not match"
                        ));
                return new LoginCustomerResponse(errors);   // customer found, passwords mismatched
            }
        } else {
            errors.add(
                    new CoreError(
                            "customerEmail",
                            "Customer not found"
                    ));
            return new LoginCustomerResponse(errors);   // customer not found
        }
    }
}
