package internet_store.core.services.customer;

import internet_store.core.requests.customer.AddCustomerRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.customer.AddCustomerResponse;
import internet_store.core.services.customer.validators.AddCustomerRequestValidator;
import internet_store.database.jpa.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Component
@Transactional
public class AddCustomerService {

    @Autowired private CustomerRepository customerDatabase;
    @Autowired private AddCustomerRequestValidator addCustomerRequestValidator;

    public AddCustomerResponse execute (AddCustomerRequest addCustomerRequest){

        List<CoreError> errors = addCustomerRequestValidator.validate(addCustomerRequest);
        if (!errors.isEmpty()){
            return new AddCustomerResponse(errors);
        }

        customerDatabase.save(addCustomerRequest.getCustomer());
        return new AddCustomerResponse(addCustomerRequest.getCustomer());
    }
}