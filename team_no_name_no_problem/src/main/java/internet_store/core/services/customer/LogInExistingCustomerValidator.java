package internet_store.core.services.customer;
/*
import internet_store.core.requests.customer.LogInCustomerRequest;
import internet_store.core.response.CoreError;

import java.util.ArrayList;
import java.util.List;

public class LogInExistingCustomerValidator {

    public List<CoreError> validate (LogInCustomerRequest logInCustomerRequest){
        List<CoreError> errors = new ArrayList<>();

        if (logInCustomerRequest.getExistingCustomer().getName() == null || logInCustomerRequest.getExistingCustomer().getName().isEmpty()){
            errors.add(new CoreError("name","Not valid input for name"));
        }
        if (logInCustomerRequest.getExistingCustomer().getSurname() == null ||
                logInCustomerRequest.getExistingCustomer().getSurname().isEmpty()){
            errors.add(new CoreError("surname", "Not valid input for surname"));
        }
        if (logInCustomerRequest.getExistingCustomer().getPhoneNumber() == null ||
                logInCustomerRequest.getExistingCustomer().getPhoneNumber().isEmpty()){
            errors.add(new CoreError("phone number", "Not valid input for phone number"));
        }
        return errors;
    }
}
*/