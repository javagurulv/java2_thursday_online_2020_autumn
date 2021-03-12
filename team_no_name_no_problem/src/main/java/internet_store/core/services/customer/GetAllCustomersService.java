package internet_store.core.services.customer;

import internet_store.core.requests.customer.GetAllCustomersRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.customer.GetAllCustomersResponse;
import internet_store.core.services.customer.validators.GetAllCustomersValidator;
import internet_store.core.domain.Customer;
import internet_store.database.jpa.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Component
@Transactional
public class GetAllCustomersService {

    @Autowired private CustomerRepository customerDatabase;
    @Autowired private GetAllCustomersValidator getAllCustomersValidator;

    public GetAllCustomersResponse execute(GetAllCustomersRequest getAllCustomersRequest){
        List<CoreError> errors = getAllCustomersValidator.validate(getAllCustomersRequest);

        if (customerDatabase.findAll().isEmpty()){
            errors.add(new CoreError("database", "Customer database is empty"));
            return new GetAllCustomersResponse(errors, new ArrayList<>());
            }

        List<Customer> customers = customerDatabase.findAll();
        return new GetAllCustomersResponse(customers);
        }
}
