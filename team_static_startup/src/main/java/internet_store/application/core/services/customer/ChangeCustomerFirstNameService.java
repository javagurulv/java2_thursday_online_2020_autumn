package internet_store.application.core.services.customer;

import internet_store.application.core.database.customer.CustomerRepository;
import internet_store.application.core.database.jpa.JpaCustomerRepository;
import internet_store.application.core.requests.customer.ChangeCustomerFirstNameRequest;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.customer.ChangeCustomerFirstNameResponse;
import internet_store.application.core.services.customer.validators.ChangeCustomerFirstNameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChangeCustomerFirstNameService {

    @Autowired private JpaCustomerRepository customerRepository;
    @Autowired private ChangeCustomerFirstNameValidator validator;

    public ChangeCustomerFirstNameResponse execute(ChangeCustomerFirstNameRequest request){

        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()){
            return new ChangeCustomerFirstNameResponse(errors);
        }

        return new ChangeCustomerFirstNameResponse(
                customerRepository.changeFirstName(request.getId(), request.getCustomerNewName()) == 1);
    }

}
