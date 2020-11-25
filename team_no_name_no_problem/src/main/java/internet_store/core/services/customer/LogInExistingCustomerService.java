package internet_store.core.services.customer;
/*
import internet_store.core.requests.customer.LogInCustomerRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.customer.LoginExistingCustomerResponse;
import internet_store.database.customer.CustomerDatabase;

import java.util.List;

public class LogInExistingCustomerService {

    private final CustomerDatabase customerDatabase;
    private final LogInExistingCustomerValidator logInExistingCustomerValidator;

    public LogInExistingCustomerService(CustomerDatabase customerDatabase,
                                        LogInExistingCustomerValidator logInExistingCustomerValidator) {
        this.customerDatabase = customerDatabase;
        this.logInExistingCustomerValidator = logInExistingCustomerValidator;

    }

    public LoginExistingCustomerResponse execute(LogInCustomerRequest logInCustomerRequest) {

        List<CoreError> errors = logInExistingCustomerValidator.validate(logInCustomerRequest);
        if (!errors.isEmpty()) {
            return new LoginExistingCustomerResponse(errors);
        }
        return new LoginExistingCustomerResponse(LogInCustomerRequest.getExistingCustomer);
    }
}
*/