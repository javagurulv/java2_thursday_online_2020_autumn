package internet_store.core.services.customer;

import internet_store.core.domain.Customer;
import internet_store.core.requests.customer.DeleteCustomerRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.customer.DeleteCustomerResponse;
import internet_store.core.services.customer.validators.DeleteCustomerRequestValidator;
import internet_store.database.customer.CustomerDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component public class DeleteCustomerService {

    @Autowired private CustomerDatabase customerDatabase;
    @Autowired private DeleteCustomerRequestValidator deleteCustomerRequestValidator;

    public DeleteCustomerResponse execute (DeleteCustomerRequest deleteCustomerRequest){
        List<CoreError> errors = deleteCustomerRequestValidator.validate(deleteCustomerRequest);

        if (!errors.isEmpty()){
            return new DeleteCustomerResponse(errors);
        }

        if (customerDatabase.containsId(deleteCustomerRequest.getId())) {
            for (int i = 0; i < customerDatabase.getCustomers().size(); i++) {
                if (getCurrentCustomer(i).getId() == deleteCustomerRequest.getId()) {
                    customerDatabase.deleteCustomerById(deleteCustomerRequest.getId());
                    return new DeleteCustomerResponse(deleteCustomerRequest.getId());
                }
            }
        }

        errors.add(new CoreError("database", "database doesn't contain this customer "
        + deleteCustomerRequest.getId()));
        return new DeleteCustomerResponse(errors);
    }

    private Customer getCurrentCustomer (int index){
        return customerDatabase.getCustomers().get(index);
    }
}
