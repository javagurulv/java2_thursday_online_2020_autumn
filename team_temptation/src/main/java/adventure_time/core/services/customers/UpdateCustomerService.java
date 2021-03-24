package adventure_time.core.services.customers;

import adventure_time.core.domain.Customers;
import adventure_time.core.requests.customers.UpdateCustomerRequest;
import adventure_time.core.responses.CoreError;
import adventure_time.core.responses.customer.UpdateCustomerResponse;
import adventure_time.core.database.customers.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UpdateCustomerService {

    @Autowired
    private CustomerRepository database;
    @Autowired
    private UpdateCustomerRequestValidator validatorUpdate;

    public UpdateCustomerResponse updateCustomer (UpdateCustomerRequest request) {

        List<CoreError> errors = validatorUpdate.validate(request);
        if (!errors.isEmpty()) {
            return new UpdateCustomerResponse(errors);
        }

        Customers customer = new Customers(
                choseValue(request.getName(), request.getCustomer().getCustomerName()),
                choseValue(request.getEmail(), request.getCustomer().getCustomerEmail()),
                choseValue(request.getPhone(), request.getCustomer().getCustomerPhone()),
                choseValue(request.getPassword(), request.getCustomer().getCustomerPassword())
        );

        if (database.updateCustomer(customer, request.getCustomer().getCustomerId())) {
            return new UpdateCustomerResponse();
        }

        CoreError error = new CoreError("customerEmail", "Sorry, in DB already exist the user with email '" +
                customer.getCustomerEmail() + "'. You have to choose another one.");
        errors.add(error);
        return new UpdateCustomerResponse(errors);

    }

    private String choseValue (String newValue, String oldValue) {
        return newValue.equals("") ? oldValue : newValue;
    }

}
