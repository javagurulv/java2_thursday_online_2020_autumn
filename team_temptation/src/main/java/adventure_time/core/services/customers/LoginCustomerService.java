package adventure_time.core.services.customers;

import adventure_time.core.domain.Customers;
import adventure_time.core.requests.customers.LoginCustomerRequest;
import adventure_time.core.responses.CoreError;
import adventure_time.core.responses.customer.LoginCustomerResponse;
import adventure_time.core.database.customers.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class LoginCustomerService {

    @Autowired
    private CustomerRepository database;
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
                    equals(request.getPassword())) {
                return new LoginCustomerResponse(customer.get());   // customer found, passwords matched
            } else {
                errors.add
                        (new CoreError(
                                "customerPassword",
                                "Log in: Passwords did not match"
                        ));
                return new LoginCustomerResponse(errors);   // customer found, passwords mismatched
            }
        } else {
            errors.add(
                    new CoreError(
                            "customerEmail",
                            "Log in: Customer not found"
                    ));
            return new LoginCustomerResponse(errors);   // customer not found
        }
    }
}
