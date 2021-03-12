package internet_store.core.services.customer;

import internet_store.core.domain.Customer;
import internet_store.core.requests.customer.DeleteCustomerRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.customer.DeleteCustomerResponse;
import internet_store.core.services.customer.validators.DeleteCustomerRequestValidator;
import internet_store.database.jpa.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Component
@Transactional
public class DeleteCustomerService {

    @Autowired private CustomerRepository customerDatabase;
    @Autowired private DeleteCustomerRequestValidator deleteCustomerRequestValidator;

    public DeleteCustomerResponse execute (DeleteCustomerRequest deleteCustomerRequest){
        List<CoreError> errors = deleteCustomerRequestValidator.validate(deleteCustomerRequest);

        if (!errors.isEmpty()){
            return new DeleteCustomerResponse(errors);
        }

        if (customerDatabase.existsById(deleteCustomerRequest.getId())) {
            for (int i = 0; i < customerDatabase.findAll().size(); i++) {
                if (getCurrentCustomer(i).getId() == deleteCustomerRequest.getId()) {
                    customerDatabase.deleteById(deleteCustomerRequest.getId());
                    return new DeleteCustomerResponse(deleteCustomerRequest.getId());
                }
            }
        }

        errors.add(new CoreError("database", "database doesn't contain this customer "
        + deleteCustomerRequest.getId()));
        return new DeleteCustomerResponse(errors);
    }

    private Customer getCurrentCustomer (int index){
        return customerDatabase.findAll().get(index);
    }
}
