package lv.tssweb.estore.core.services.customer;

import lv.tssweb.estore.core.database.customer.CustomerRepository;
import lv.tssweb.estore.core.domain.Customer;
import lv.tssweb.estore.core.requests.customer.AddCustomerRequest;
import lv.tssweb.estore.core.responses.CoreError;
import lv.tssweb.estore.core.responses.customer.AddCustomerResponse;
import lv.tssweb.estore.core.services.customer.validators.AddCustomerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddCustomerService {

    @Autowired private CustomerRepository customerRepository;
    @Autowired private AddCustomerValidator validator;

    public AddCustomerResponse execute(AddCustomerRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddCustomerResponse(errors);
        }
        Customer customer = new Customer(request.getCustomerFirstName(), request.getCustomerSecondName());
        customer.setCustomerPhone(request.getCustomerPhone());
        customer.setCustomerEmail(request.getCustomerEMail());
        customer.setCustomerAddress(request.getCustomerAddress());

        customerRepository.addCustomer(customer);
        return new AddCustomerResponse(customer);
    }
}
